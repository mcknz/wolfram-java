package com.mcknz.wolfram.pages;

import com.mcknz.wolfram.web.WebPage;

public class WolframPage extends WebPage {

  public WolframPage() {
    super("https://www.wolframalpha.com/");
  }

  public void go() {
    navigateToStartingUrl();
    pause();
  }

  public void ask(String question) {
    String questionTextXPath = "//input[@placeholder='Enter what you want to calculate or know about']";
    String questionButtonXPath = "//button[@type='submit']";

    enterTextByXPath(questionTextXPath, question);
    pause();

    clickByXPath(questionButtonXPath);
    pause();
  }

  public String getAnswer() {
    String rootXPath = "//*[@id=\"root\"]/div/div/main/div[3]/div/div[1]/section/";
    String answerDivXPath = String.format("%s%s", rootXPath, "section");
    String answerDivXPath2 = String.format("%s%s", rootXPath, "section[2]");
    String answerButtonXPath = String.format("%s%s", rootXPath, "section[2]/ul/li[3]/button");
    String answerTextXPath = String.format("%s%s", rootXPath, "section[3]/div[1]/div/button/span");

    mouseoverByXPath(answerDivXPath);
    mouseoverByXPath(answerDivXPath2);
    pause();
    clickByXPath(answerButtonXPath);
    pause();

    return getTextByXPath(answerTextXPath);
  }

  private void pause() {
    try {
      Thread.sleep(1000);
    } catch(Exception e) {
      //sleep for display purposes, don't need to handle exception.
    }
  }
}
