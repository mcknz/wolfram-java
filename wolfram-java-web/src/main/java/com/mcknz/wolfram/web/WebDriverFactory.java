package com.mcknz.wolfram.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
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
          return getChromeDriver(settings, false);
        case CHROME_HEADLESS:
          return getChromeDriver(settings, true);
      }
    } catch(Exception ex) {
      throw new IllegalArgumentException("Unable to create Driver for the specified type.");
    }
    return null;
  }

  private RemoteWebDriver getChromeDriver(Settings settings,
                                          boolean isHeadless) throws IOException {
    ChromeOptions options = new ChromeOptions();
    if(isHeadless) {
      options.addArguments("--headless");
    }
    ChromeDriverService service =
      new ChromeDriverService
        .Builder()
        .usingDriverExecutable(
          new File(
            settings.getDriverPath(),
            getDriverFile(settings,"chromedriver")
          )
        )
        .usingAnyFreePort()
        .build();

    service.start();

    return new RemoteWebDriver(service.getUrl(), options);
  }

  @SuppressWarnings("SameParameterValue")
  private String getDriverFile(Settings settings,
                               String name) {
    if(settings.isWindows()) {
      return name + ".exe";
    }
    return name;
  }
}