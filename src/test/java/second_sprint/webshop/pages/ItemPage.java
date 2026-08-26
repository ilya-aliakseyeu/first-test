package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import net.datafaker.Faker;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ItemPage extends BasePage {
  private final Faker faker = new Faker();

  private final static SelenideElement qtyField = $("input#addtocart_72_EnteredQuantity");
  private final static SelenideElement itemName = $("h1");
  private final static SelenideElement itemPrice = $(".price-value-72");
  private final static SelenideElement addToCartButton = $("#add-to-cart-button-72");
  private final static ElementsCollection processorsBox = $("ul.option-list").$$("li input");
  private final static SelenideElement notificationBar = $("div#bar-notification");

  private String expectedName;
  private String expectedUnitPrice;
  private String itemQty;

  public ItemPage setRandomItemQty() {
    itemQty = String.valueOf(faker.number().numberBetween(1, 10));
    expectedName = getItemName();
    expectedUnitPrice = getItemPrice();
    qtyField.setValue(itemQty);

    return this;
  }

  public ItemPage clickAddToCart() {
    addToCartButton.click();

    return this;
  }

  public ItemPage setProcessor(int index) {
    processorsBox.get(index).click();
    if (index == 1) {
      expectedUnitPrice = String.valueOf(Double.parseDouble(expectedUnitPrice) + 15.0);
    } else if (index == 2) {
      expectedUnitPrice = String.valueOf(Double.parseDouble(expectedUnitPrice) + 100.0);
    }

    return this;
  }

  public ItemPage checkNotificationBar() {
    notificationBar.shouldBe(visible);

    return this;
  }

  public ShoppingCartPage clickShoppingCartButton() {
    shoppingCartButton.click();

    return new ShoppingCartPage(new ItemData(expectedName, expectedUnitPrice, itemQty));
  }

  public String getItemName() {
    return itemName.getText();
  }

  public String getItemPrice() {
    return itemPrice.getText();
  }
}