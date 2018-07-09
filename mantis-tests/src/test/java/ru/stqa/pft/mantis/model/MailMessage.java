package ru.stqa.pft.mantis.model;

/**
 * Created by admin on 09.07.2018.
 */
public class MailMessage {

  public String to;
  public String text;

  public MailMessage(String to, String text){
    this.to=to;
    this.text=text;
  }
}
