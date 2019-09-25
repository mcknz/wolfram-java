package com.mcknz.wolfram.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class WebDriverFactory {

  @SuppressWarnings("SameParameterValue")
  WebDriver getDriver(Settings settings) {
    return getWebDriver(settings);
  }

  private WebDriver getWebDriver(Settings settings) {
    try {
      switch (settings.getDriverType()) {
        case CHROME:
          return getChromeDriver(settings,false);
        case CHROME_HEADLESS:
          return getChromeDriver(settings, true);
      }
    } catch(Exception ex) {
      throw new IllegalArgumentException("Unable to create Driver for the specified type.", ex);
    }
    return null;
  }

  private WebDriver getChromeDriver(Settings settings,
                                    boolean isHeadless) {
    ChromeOptions options = new ChromeOptions();
    if(isHeadless) {
      options.addArguments("--headless", "--window-size=1920,1080");
    }
    System.setProperty("webdriver.chrome.driver", settings.getDriverPathAndName());
    return new ChromeDriver(options);
  }
}