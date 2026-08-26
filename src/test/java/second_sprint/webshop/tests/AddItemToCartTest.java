package second_sprint.webshop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.ItemPage;
import second_sprint.webshop.pages.ShoppingCartPage;
import second_sprint.webshop.pages.WsWelcomePage;
import second_sprint.webshop.steps.AuthStep;

import static com.codeborne.selenide.Selenide.open;
import static java.util.Locale.US;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static second_sprint.webshop.tests.WebShopConfig.BASE_URL;

public class AddItemToCartTest extends BaseTest {

  /** Товар выбирает тест - значит тест и знает, что он выбрал. */
  private static final String PRODUCT_NAME = "Build your own cheap computer";

  private final AuthStep authStep = new AuthStep();

  @BeforeEach
  void setUp() {
    authStep.register();
  }

  @Test
  void addItemToCartTest() {
    String processor = "Fast";
    String quantity = "3";

    ItemPage itemPage = open(BASE_URL, WsWelcomePage.class)
        .hoverComputersTab()
        .clickDesktopButton()
        .openProduct(PRODUCT_NAME);

    float expectedUnitPrice = Float.parseFloat(itemPage.getItemPrice()) + processorSurcharge(processor);

    ShoppingCartPage cartPage = itemPage
        .selectProcessor(processor)
        .setQuantity(quantity)
        .clickAddToCart()
        .checkAddedToCartNotification()
        .checkCartQuantity(quantity)
        .openCart();

    String expectedUnitPriceText = String.format(US, "%.2f", expectedUnitPrice);
    String expectedSubtotalText = String.format(US, "%.2f",
        expectedUnitPrice * Float.parseFloat(quantity));

    assertAll(
        () -> assertEquals(PRODUCT_NAME, cartPage.getItemName()),
        () -> assertEquals(quantity, cartPage.getQuantity()),
        () -> assertEquals(expectedUnitPriceText, cartPage.getUnitPrice()),
        () -> assertEquals(expectedSubtotalText, cartPage.getSubtotal())
    );
  }

  /** Надбавки - тестовые данные, их знает тест, а не страница. */
  private float processorSurcharge(String processor) {
    return switch (processor) {
      case "Slow" -> 0f;
      case "Medium" -> 15f;
      case "Fast" -> 100f;
      default -> throw new IllegalArgumentException("Unknown processor: " + processor);
    };
  }
}
