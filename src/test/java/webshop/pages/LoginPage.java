package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
  private static final SelenideElement emailField = $("input#Email");
  private static final SelenideElement passwordField = $("input#Password");
  private static final SelenideElement rememberMeButton = $("input#RememberMe");
  private static final SelenideElement logInButton = $("input.button-1.login-button");

  public LoginPage inputEmail(String email) {
    emailField.setValue(email);
    return this;
  }

  public LoginPage inputPassword(String password) {
    passwordField.setValue(password);
    return this;
  }

  public LoginPage clickRememberMe() {
    rememberMeButton.click();
    return this;
  }

  public WsWelcomePage clickLogInButton() {
    logInButton.click();
    return new WsWelcomePage();
  }
}
