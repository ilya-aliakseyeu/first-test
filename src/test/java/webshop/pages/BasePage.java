package webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
  private static final SelenideElement registerButton = $(".ico-register");
  private static final ElementsCollection headerElements = $$("div.header-links ul li a");
  private static final SelenideElement computerTab = $$("ul.top-menu li a").get(1);
  private static final SelenideElement desktopButton = $(byText("Desktops"));
  private static final SelenideElement shoppingCartButton =  $("span.cart-label");



  public RegistrationPage registerButtonClick() {
    registerButton.click();
    return new RegistrationPage();
  }

  public BasePage emailShown(String email) {
    headerElements.get(0).shouldHave(text(email));
    return this;
  }

  public BasePage hoverComputersTab() {
    computerTab.hover();
    return this;
  }

  public DesktopsPage clickDesktopButton() {
    desktopButton.click();
    return new DesktopsPage();
  }

  public ShoppingCartPage clickShoppingCartButton() {
    shoppingCartButton.click();
    return new ShoppingCartPage();
  }
}
