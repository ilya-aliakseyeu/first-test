package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Карточка товара. Никакого состояния: пейдж кликает, вводит и отдает то,
 * что показано на экране. Ожидания и расчеты - забота теста.
 */
public class ItemPage extends BasePage {
  private final SelenideElement itemName = $("div.product-name h1");
  private final SelenideElement itemPrice = $("div.product-price span");
  private final SelenideElement qtyField = $("input.qty-input");
  private final SelenideElement addToCartButton = $("input.add-to-cart-button");
  private final SelenideElement attributes = $("div.attributes");
  private final SelenideElement barNotification = $("div#bar-notification");

  /** Выбор по видимому названию и в границах нужной группы атрибутов, а не по индексу. */
  public ItemPage selectProcessor(String processor) {
    attributeOptions("Processor")
        .findBy(text(processor))
        .$("input")
        .click();

    return this;
  }

  private ElementsCollection attributeOptions(String groupTitle) {
    return attributes.$$("dl dt")
        .findBy(text(groupTitle))
        .sibling(0)     // dt -> соответствующий dd
        .$$("li");
  }

  public ItemPage setQuantity(String quantity) {
    qtyField.setValue(quantity);

    return this;
  }

  public ItemPage clickAddToCart() {
    addToCartButton.click();

    return this;
  }

  /** Плашка одна на успех и на ошибку, поэтому проверяем класс и текст, а не только видимость. */
  public ItemPage checkAddedToCartNotification() {
    barNotification.shouldBe(visible)
        .shouldHave(cssClass("success"))
        .shouldHave(text("The product has been added to your shopping cart"));

    return this;
  }

  public String getItemName() {
    return itemName.getText();
  }

  public String getItemPrice() {
    return itemPrice.getText();
  }
}
