package webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage {
  private final static SelenideElement cheapComputerTicket = $$("div.product-item").get(0);

  @Step("Кликнуть на тикет компьютера")
  public ItemPage clickComputerTicket() {
    cheapComputerTicket.click();
    return new ItemPage();
  }
}
