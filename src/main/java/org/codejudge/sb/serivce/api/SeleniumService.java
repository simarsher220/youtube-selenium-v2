package org.codejudge.sb.serivce.api;

import org.codejudge.sb.model.ResultMetadata;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SeleniumService {
    public WebDriver getWebDriver();
    public void searchVideos(WebDriver driver, String searchTitle);
    public ResultMetadata getSentiments(WebDriver driver, String url) throws InterruptedException;
}
