package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ConfirmRegistrationPage extends BasePage {
  private static final SelenideElement confirmText = $(".result");

  @Step("Проверить текст подтверждения регистрации")
  public ConfirmRegistrationPage confirmRegistrationText() {
    confirmText.shouldHave(text("Your registration completed"));
    return this;
  }
}
