package org.codejudge.sb.serivce.impl;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.model.ResultMetadata;
import org.codejudge.sb.serivce.api.SeleniumService;
import org.openqa.selenium.*;
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement playButton = driver.findElement(By.cssSelector("button.ytp-play-button.ytp-button"));
        playButton.click();
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"count\"]"));
        String sentimentString = null;
        for (WebElement element : elements) {
            String commentsString = element.getText();
            if (commentsString.toLowerCase().contains("comments")) {
                sentimentString = commentsString.toLowerCase().replace("comments", "").trim();
                break;
            }
        }
        Integer comments = Integer.parseInt(sentimentString);
        Thread.sleep(10000);
        playButton.click();
        log.info("Reached here");
        return new ResultMetadata(url, comments);
    }

    @Override
    public  WebDriver getWebDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "/Users/simarsi/Downloads/chromedriver");
        chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--incognito", "--disable-extensions", "--disable-gpu");
        chromeOptions.addArguments("window-size=1280,800");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return new ChromeDriver(chromeOptions);
    }

    @Override
    public void searchVideos(WebDriver driver, String searchTitle) {

    }
}
