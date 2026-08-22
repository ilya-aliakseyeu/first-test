package webshop.tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webshop.pages.WsWelcomePage;
import webshop.steps.AuthStep;

import static com.codeborne.selenide.Selenide.*;
import static webshop.TestData.BASE_URL;

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
