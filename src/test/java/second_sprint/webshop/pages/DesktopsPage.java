package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage extends BasePage {
  private final static SelenideElement cheapComputerTicket = $$("div.product-item").get(0);


  public ItemPage clickComputerTicket() {
    cheapComputerTicket.click();

    return new ItemPage();
  }
}