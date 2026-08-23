package webshop.tests;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import webshop.pages.WsWelcomePage;
import webshop.steps.AuthStep;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static webshop.TestData.BASE_URL;

public class AddItemToCartTest extends BaseTest {
  static AuthStep authStep = new AuthStep();

  @BeforeEach
  public static void setUp() {
    authStep.register();
  }

  @Test
  @DisplayName("Успешное добавление товара в корзину")
  @Severity(CRITICAL)
  @Owner("ilya-alekseev")
  @Link(name = "TASK-228")
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
