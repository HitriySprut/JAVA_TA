package ru.stqa.pft.mantis.model;

import java.util.Date;

/**
 * Created by admin on 09.07.2018.
 */
public class MailMessage {

  public String to;
  public String text;
  public Date date;

  public MailMessage(String to, String text, Date date){
    this.to=to;
    this.text=text;
    this.date=date;
  }
}
