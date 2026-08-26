package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class ConfirmRegistrationPage extends BasePage {
  private final SelenideElement confirmText = $(".result");

  public ConfirmRegistrationPage confirmRegistrationText() {
    confirmText.shouldHave(exactText("Your registration completed"));

    return this;
  }
}
