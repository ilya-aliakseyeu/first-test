package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Конструктор без аргументов: пейдж снова можно получить через
 * open(CART_URL, ShoppingCartPage.class) и page(ShoppingCartPage.class).
 * Проверок здесь нет - только геттеры того, что показано в корзине.
 */
public class ShoppingCartPage extends BasePage {
  private final SelenideElement productName = $(".product-name");
  private final SelenideElement productUnitPrice = $(".product-unit-price");
  private final SelenideElement quantityInput = $("input.qty-input");
  private final SelenideElement productSubtotal = $(".product-subtotal");

  public String getItemName() {
    return productName.getText();
  }

  public String getQuantity() {
    return quantityInput.getValue();
  }

  public String getUnitPrice() {
    return productUnitPrice.getText();
  }

  public String getSubtotal() {
    return productSubtotal.getText();
  }
}
