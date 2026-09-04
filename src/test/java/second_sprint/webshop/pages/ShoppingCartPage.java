package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage extends BasePage {
  private final SelenideElement productName = $(".product-name");
  private final SelenideElement qtyInput = $(".qty-input");
  private final SelenideElement productUnitPrice = $(".product-unit-price");
  private final SelenideElement productSubtotal = $(".product-subtotal");

  public String getItemName() {
    return productName.getText();
  }

  public String getQuantity() {
    return qtyInput.getValue();
  }

  public String getUnitPrice() {
    return productUnitPrice.getText();
  }

  public String getSubtotal() {
    return productSubtotal.getText();
  }
}
