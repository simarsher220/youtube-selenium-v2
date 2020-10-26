package org.codejudge.sb.serivce.impl;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.model.ResultMetadata;
import org.codejudge.sb.serivce.api.SeleniumService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SeleniumServiceImpl implements SeleniumService {

    @Override
    public ResultMetadata getSentiments(WebDriver driver, String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        log.info("Reached here");
        WebElement playButton = driver.findElement(By.cssSelector("button.ytp-play-button.ytp-button"));
        playButton.click();
        List<WebElement> elements = driver.findElements(By.cssSelector("yt-formatted-string#text.style-scope.ytd-toggle-button-renderer.style-text"));
        String text = elements.get(0).getText();
        Integer likes = Integer.parseInt(text);
        text = elements.get(1).getText();
        Integer disLikes = Integer.parseInt(text);
        Thread.sleep(10000);
        playButton.click();
        log.info("Reached here");
        return new ResultMetadata(url, likes, disLikes);
    }

    @Override
    public  WebDriver getWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "/Users/simarsi/Downloads/chromedriver");
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--incognito", "--disable-extensions", "--disable-gpu");
        chromeOptions.addArguments("start-maximized");
//        chromeOptions.addArguments("window-size=1280,800");
//        chromeOptions.setExperimentalOption("useAutomationExtension", false);
//        URL liveRunUrl = new URL("http://localhost:4444/wd/hub");
        return new ChromeDriver(chromeOptions);
    }

    @Override
    public void searchVideos(WebDriver driver, String searchTitle) {

    }
}
