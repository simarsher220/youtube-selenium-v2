package org.codejudge.sb.serivce.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.codejudge.sb.dao.api.AppRepository;
import org.codejudge.sb.dao.api.AppV2Repository;
import org.codejudge.sb.entity.Sentiment;
import org.codejudge.sb.entity.SentimentV2;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.EvalRequest;
import org.codejudge.sb.model.ProcStatus;
import org.codejudge.sb.model.ResultMetadata;
import org.codejudge.sb.serivce.api.AppService;
import org.codejudge.sb.serivce.api.SeleniumService;
import org.codejudge.sb.util.UrlValidationUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private AppRepository appRepo;

    @Autowired
    private AppV2Repository appV2Repo;

    @Autowired
    private SeleniumService seleniumService;

    @Override
    public Sentiment initiate(EvalRequest request) throws CustomException {
        log.info("Got request to initiate the sentiment processing for request: {}", request);
        EvalRequest.validate(request);
        Sentiment sentiment = new Sentiment.SentimentBuilder()
                .searchTitle(request.getSearchTitle())
                .status(ProcStatus.PROCESSING).build();
        sentiment = appRepo.saveAndFlush(sentiment);
        Integer id = sentiment.getId();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            WebDriver driver = seleniumService.getWebDriver();
            driver.get("https://www.youtube.com/results?search_query=" + request.getSearchTitle());
            List<WebElement> elements = driver.findElements(By.id("video-title"));
            ResultMetadata results = null;
            try {
                String url = elements.get(0).getAttribute("href");
                results = seleniumService.getSentiments(driver, url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Sentiment sentiment1 = appRepo.getById(id);
            sentiment1.setDislikes(results.getDisLikes());
            sentiment1.setLikes(results.getLikes());
            sentiment1.setVideoUrl(results.getVideoLink());
            sentiment1.setStatus(ProcStatus.PROCESSED);
            appRepo.save(sentiment1);
            driver.quit();
        });
        return sentiment;
    }

    @Override
    public Sentiment getSentiments(Integer id) throws CustomException {
        Sentiment sentiment = appRepo.getById(id);
        if (null == sentiment) {
            throw new CustomException("No record found!", HttpStatus.NOT_FOUND);
        }
        return sentiment;
    }

    @Override
    public SentimentV2 initiateV2(String url) throws CustomException {
        log.info("Got request to initiate the sentiment processing for url: {}", url);
        if (StringUtils.isEmpty(url)) {
            throw new CustomException("Url can't be empty!", HttpStatus.BAD_REQUEST);
        }
        UrlValidationUtil.validate(url);
        WebDriver driver = seleniumService.getWebDriver();
        ResultMetadata results = null;
        try {
            results = seleniumService.getSentiments(driver, url);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SentimentV2 sentiment = new SentimentV2.SentimentBuilderV2()
                .likes(results.getLikes())
                .dislikes(results.getDisLikes())
                .videoUrl(results.getVideoLink()).build();
        sentiment = appV2Repo.save(sentiment);
        driver.quit();
        return sentiment;
    }
}
