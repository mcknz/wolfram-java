package com.mcknz.wolfram.web;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class Settings {

  private final Properties props;
  private String driverPath;
  private WebDriverType driverType;
  private int pageTimeout = -1;
  private String operatingSystem;

  public Settings() {
    props = new Properties();
    loadResourceSettings(Thread.currentThread().getContextClassLoader());
  }

  public String getDriverPath() {
    if(driverPath == null) {
      driverPath = getProperty("driverPath");
    }
    return driverPath;
  }

  public WebDriverType getDriverType() {
    if(driverType == null) {
      driverType = WebDriverType.valueOf(getProperty("driverType"));
    }
    return driverType;
  }

  public int getPageTimeout() {
    if(pageTimeout == -1) {
      pageTimeout = Integer.parseInt(getProperty("pageTimeout"));
    }
    return pageTimeout;
  }

  public boolean isWindows() {
    return getOperatingSystem().toLowerCase().contains("windows");
  }

  private String getOperatingSystem() {
    if(operatingSystem == null) {
      operatingSystem = getProperty("operatingSystem");
    }
    return operatingSystem;
  }

  private void loadResourceSettings(ClassLoader loader) {
    Properties p = new Properties();
    try {
      final Enumeration<URL> systemResources =
        (loader == null ? ClassLoader.getSystemClassLoader() : loader)
          .getResources("settings.properties");
      while (systemResources.hasMoreElements()) {
        p.load(systemResources.nextElement().openStream());
      }
    } catch (IOException ex) {
      // ignore if running as part of CI process
      //  because all properties will be supplied externally
    }
    props.putAll(p);
  }

  private String getProperty(String key) {
    String property = System.getProperty(key);
    if(property == null) {
      return props.getProperty(key);
    }
    return property;
  }
}