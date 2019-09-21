package com.mcknz.wolfram.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;

class WebDriverFactory {

  @SuppressWarnings("SameParameterValue")
  WebDriver getDriver(Settings settings) {
    return getWebDriver(settings);
  }

  private RemoteWebDriver getWebDriver(Settings settings) {
    try {
      switch (settings.getDriverType()) {
        case CHROME:
          return getChromeDriver(false);
        case CHROME_HEADLESS:
          return getChromeDriver(true);
      }
    } catch(Exception ex) {
      throw new IllegalArgumentException("Unable to create Driver for the specified type.");
    }
    return null;
  }

  private RemoteWebDriver getChromeDriver(boolean isHeadless) throws IOException {
    ChromeOptions options = new ChromeOptions();
    if(isHeadless) {
      options.addArguments("--headless");
    }
    ChromeDriverService service =
      new ChromeDriverService
        .Builder()
        .usingAnyFreePort()
        .build();

    service.start();

    return new RemoteWebDriver(service.getUrl(), options);
  }
}