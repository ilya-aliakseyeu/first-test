package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ConfirmRegistrationPage extends BasePage {
  private static final SelenideElement confirmText = $(".result");

  public ConfirmRegistrationPage confirmRegistrationText() {
    confirmText.shouldHave(text("Your registration completed"));
    return this;
  }
}
