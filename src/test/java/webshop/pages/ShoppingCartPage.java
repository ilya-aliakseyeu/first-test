package webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ShoppingCartPage extends BasePage {
  private static final SelenideElement productName = $(".product-name");
  private static final SelenideElement productPrice = $(".product-subtotal");

  public ShoppingCartPage checkProductName() {
    productName.shouldBe(text(ItemPage.expectedName));
    return this;
  }

  public ShoppingCartPage checkProductPrice() {
    double expectedPrice = Double.parseDouble(ItemPage.expectedUnitPrice) * Double.parseDouble(ItemPage.itemQty);
    productPrice.shouldHave(text(String.valueOf(expectedPrice)));
    return this;
  }

}
