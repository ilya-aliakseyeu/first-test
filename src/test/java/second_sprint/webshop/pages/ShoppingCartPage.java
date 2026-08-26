package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage extends BasePage {
  private static final SelenideElement productName = $(".product-name");
  private static final SelenideElement productPrice = $(".product-subtotal");

  private final ItemData itemData;

  public ShoppingCartPage(ItemData itemData) {
    this.itemData = itemData;
  }

  public ShoppingCartPage checkProductName() {
    productName.shouldHave(text(itemData.getName()));

    return this;
  }

  public ShoppingCartPage checkProductPrice() {
    double expectedPrice = Double.parseDouble(itemData.getUnitPrice()) * Double.parseDouble(itemData.getQty());
    productPrice.shouldHave(text(String.valueOf(expectedPrice)));

    return this;
  }
}