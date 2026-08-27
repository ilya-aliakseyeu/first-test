package second_sprint.webshop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.ItemPage;
import second_sprint.webshop.pages.ShoppingCartPage;
import second_sprint.webshop.pages.WsWelcomePage;
import second_sprint.webshop.steps.AuthStep;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static second_sprint.webshop.tests.WebShopConfig.BASE_URL;

public class AddItemToCartTest {
  AuthStep authStep = new AuthStep();
  private static final String PRODUCT_NAME = "Build your own cheap computer";

  @BeforeEach
  public void setUp() {
    authStep.register();
  }

  private float processorSurcharge(String processor) {
    return switch (processor) {
      case "Slow" -> 0f;
      case "Medium" -> 15f;
      case "Fast" -> 100f;
      default -> throw new IllegalArgumentException("Unknown processor: " + processor);
    };
  }

  @Test
  public void addItemToCartTest() {
    String processor = "Fast";
    String qty = "3";

    ItemPage itemPage = open(BASE_URL, WsWelcomePage.class)
        .hoverComputersTab()
        .clickDesktopButton()
        .openProduct(PRODUCT_NAME);

    float expectedUnitPrice = Float.parseFloat(itemPage.getItemPrice()) + processorSurcharge(processor);

    ShoppingCartPage cartPage = itemPage
        .selectProcessor(processor)
        .setQty(qty)
        .clickAddToCart()
        .checkSuccessNotificationBar()
        .checkCartQty(qty)
        .openCart();

    String expectedUnitPriceText = String.format(Locale.US, "%.2f", expectedUnitPrice);
    String expectedSubtotalText = String.format(Locale.US, "%.2f",
        expectedUnitPrice * Float.parseFloat(qty));

    assertAll(
        () -> assertEquals(PRODUCT_NAME, cartPage.getItemName()),
        () -> assertEquals(qty, cartPage.getQuantity()),
        () -> assertEquals(expectedUnitPriceText, cartPage.getUnitPrice()),
        () -> assertEquals(expectedSubtotalText, cartPage.getSubtotal())
    );
  }
}
