package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {

  private final SelenideElement emailField = $("input#Email");
  private final SelenideElement passwordField = $("input#Password");
  private final SelenideElement rememberMeButton = $("input#RememberMe");
  private final SelenideElement logInButton = $("input.button-1.login-button");

  @Step("Ввести электронную почту")
  public LoginPage inputEmail(String email) {
    emailField.setValue(email);

    return this;
  }

  @Step("Ввести пароль")
  public LoginPage inputPassword(String password) {
    passwordField.setValue(password);

    return this;
  }

  @Step("Указать 'Remember Me'")
  public LoginPage clickRememberMe() {
    rememberMeButton.click();

    return this;
  }

  @Step("Нажать 'Log In'")
  public WsWelcomePage clickLogInButton() {
    logInButton.click();

    return new WsWelcomePage();
  }
}
