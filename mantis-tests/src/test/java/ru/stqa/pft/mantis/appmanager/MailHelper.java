package ru.stqa.pft.mantis.appmanager;

import org.subethamail.wiser.Wiser;
import org.subethamail.smtp.*;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 09.07.2018.
 */
public class MailHelper {
  private final Wiser wiser;
  private  ApplicationManager app;

  public MailHelper(ApplicationManager app) {
    this.app=app;
    wiser= new Wiser();

  }

  public List<MailMessage> waitForMail (int count, long timeout){
    long start = System.currentTimeMillis();
    while(System.currentTimeMillis()<start+timeout){
      if(wiser.getMessages().size()>=count){
        return wiser.getMessages().stream().map((m)->toModelMail(m)).collect(Collectors.toList());
      }
      try {
        Thread.sleep(1000);}

      catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No Mail");

  }

  public static MailMessage toModelMail(WiserMessage m) {
    try {
      MimeMessage mm = m.getMimeMessage();
      return new MailMessage(mm.getAllRecipients()[0].toString(),(String) mm.getContent(),mm.getSentDate());
    }catch (MessagingException | IOException e){
      e.printStackTrace();
      return null;
    }

  }

  public void start() { wiser.start();
  }

  public void stop() { wiser.stop();
  }
}
