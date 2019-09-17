package com.mcknz.wolfram.web;

import org.openqa.selenium.WebDriver;

public class Driver {

  private static final WebDriverFactory factory = new WebDriverFactory();
  private static WebDriver driver;
  private static final Settings settings;

  static {
    settings = new Settings();
  }

  static WebDriver get() {
    if(driver == null) {
      driver = factory.getDriver(settings);
    }
    return driver;
  }

  public static Settings getSettings() {
    return settings;
  }

  public static void quit() {
    if(driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
