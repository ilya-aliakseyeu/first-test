package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
  private final SelenideElement emailField = $("input#Email");
  private final SelenideElement passwordField = $("input#Password");
  private final SelenideElement rememberMeButton = $("input#RememberMe");
  private final SelenideElement logInButton = $("input.button-1.login-button");

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
