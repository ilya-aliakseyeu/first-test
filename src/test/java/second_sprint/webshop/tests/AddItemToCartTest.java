package second_sprint.webshop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.WsWelcomePage;
import second_sprint.webshop.steps.AuthStep;

import static com.codeborne.selenide.Selenide.*;
import static second_sprint.webshop.tests.WebShopConfig.BASE_URL;

public class AddItemToCartTest {
  AuthStep authStep = new AuthStep();

  @BeforeEach
  public void setUp() {
    authStep.register();
  }

  @Test
  public void addItemToCartTest() {
    open(BASE_URL, WsWelcomePage.class)
        .hoverComputersTab()
        .clickDesktopButton()
        .clickComputerTicket()
        .setRandomItemQty()
        .setProcessor(2)
        .clickAddToCart()
        .checkNotificationBar()
        .clickShoppingCartButton()
        .checkProductName()
        .checkProductPrice();
  }
}
