package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ItemPage extends BasePage {

  private final SelenideElement itemPrice = $("span[itemprop='price']");
  private final SelenideElement qtyField = $("input.qty-input");
  private final SelenideElement addToCartButton = $("input.add-to-cart-button");
  private final SelenideElement attributes = $("div.attributes");
  private final SelenideElement successBar = $("div#bar-notification.success");
  private final SelenideElement cartQty = $("span.cart-qty");

  public ItemPage setQty(String qty) {
    qtyField.setValue(qty);

    return this;
  }

  public ItemPage clickAddToCart() {
    addToCartButton.click();

    return this;
  }

  private ElementsCollection attributeOptions(String groupTitle) {
    return attributes.$$("dl dt")
        .findBy(text(groupTitle))
        .sibling(0)
        .$$("li");
  }

  public ItemPage checkSuccessNotificationBar() {
    successBar.shouldBe(visible);

    return this;
  }

  public ItemPage checkCartQty(String qty) {
    cartQty.shouldHave(exactText("(" + qty + ")"));

    return this;
  }

  public ItemPage selectProcessor(String processor) {
    attributeOptions("Processor")
            .findBy(text(processor))
            .$("input")
            .click();

    return this;
  }

  public String getItemPrice() {
    return itemPrice.getText();
  }
}