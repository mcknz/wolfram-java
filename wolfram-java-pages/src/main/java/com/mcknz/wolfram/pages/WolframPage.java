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
    String answerDivXPath = "//*[@id=\"root\"]/div/div/main/div[3]/div/section[1]/section[2]/div[2]";
    String answerButtonXPath = "//span[text() = 'Plaintext']";
    String answerTextXPath = "//div[@aria-describedby='tooltip5']/button/span";

    mouseoverByXPath(answerDivXPath);
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
