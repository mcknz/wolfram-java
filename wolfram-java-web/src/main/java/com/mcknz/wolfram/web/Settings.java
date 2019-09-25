package com.mcknz.wolfram.web;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

class Settings {

  private final Properties props;
  private WebDriverType driverType;
  private int pageTimeout = -1;

  Settings() {
    props = new Properties();
    loadResourceSettings(Thread.currentThread().getContextClassLoader());
  }

  WebDriverType getDriverType() {
    if(driverType == null) {
      driverType = WebDriverType.valueOf(getProperty("driverType"));
    }
    return driverType;
  }

  int getPageTimeout() {
    if(pageTimeout == -1) {
      pageTimeout = Integer.parseInt(getProperty("pageTimeout"));
    }
    return pageTimeout;
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
    throw new RuntimeException(key + "|" + property);
    //if(property == null) {
    //  return props.getProperty(key);
    //}
    //return property;
  }
}