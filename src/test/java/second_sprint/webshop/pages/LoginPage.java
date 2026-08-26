package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
  private static final SelenideElement emailField = $("input#Email");
  private static final SelenideElement passwordField = $("input#Password");
  private static final SelenideElement rememberMeButton = $("input#RememberMe");
  private static final SelenideElement logInButton = $("input.button-1.login-button");
  private static final SelenideElement validationEmailError = $("[for='Email']");
  private static final SelenideElement unsuccessLoginError = $("div.validation-summary-errors");

  @Step("Ввести email")
  public LoginPage inputEmail(String email) {
    emailField.setValue(email);

    return this;
  }

  @Step("Ввести пароль")
  public LoginPage inputPassword(String password) {
    passwordField.setValue(password);

    return this;
  }

  @Step("Нажать на чекбокс 'Remember me'")
  public LoginPage clickRememberMe() {
    rememberMeButton.click();

    return this;
  }

  @Step("Нажать на кнопку 'LogIn'")
  public WsWelcomePage clickLogInButton() {
    logInButton.click();

    return new WsWelcomePage();
  }

  @Step("Проверить текст валидации ошибки email")
  public LoginPage validationEmailErrorAppear() {
    validationEmailError.shouldBe(visible);
    return this;
  }
}
