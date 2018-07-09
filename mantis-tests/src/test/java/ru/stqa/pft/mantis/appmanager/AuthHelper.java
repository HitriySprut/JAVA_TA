package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by admin on 10.07.2018.
 */
public class AuthHelper extends HelperBase {
  AuthHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
}
