package com.mcknz.wolfram.web;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class WebPage {

  private final WebDriver driver;
  private final Settings settings;
  private final String url;

  protected WebPage(String url) {
    driver = Driver.get();
    settings = Driver.getSettings();
    this.url = url;
  }

  protected void navigateToStartingUrl() {
    driver.get(url);
  }

  protected void mouseoverByXPath(String xPath) {
    Actions actions = new Actions(driver);
    actions.moveToElement(waitUntilElementClickableByXPath(xPath)).perform();
  }

  protected void enterTextByXPath(String xPath, String text) {
    waitUntilElementClickableByXPath(xPath).sendKeys(text);
  }

  protected String getTextByXPath(String xPath) {
    return waitUntilElementClickableByXPath(xPath).getText();
  }

  protected void clickByXPath(String xPath) {
    waitUntilElementClickableByXPath(xPath).click();
  }

  private WebElement waitUntilElementClickableByXPath(String xPath) {
    return waitUntilElementClickable(By.xpath(xPath));
  }

  private WebElement waitUntilElementClickable(By locator)
  {
    try
    {
      return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
    catch (NoSuchElementException ex)
    {
      System.out.print("Element with locator: '" + locator.toString() + "' was not found in current context page.");
      return null;
    }
  }

  private WebDriverWait getWait() {
    WebDriverWait wait = new WebDriverWait(driver, settings.getPageTimeout());
    wait.ignoring(StaleElementReferenceException.class);
    return wait;
  }
}