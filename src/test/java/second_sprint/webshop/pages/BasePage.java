package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Шапка сайта: она есть на любой странице, поэтому живет в базовом классе.
 * Элементы - поля экземпляра, а не static: элемент принадлежит объекту пейджа.
 */
public class BasePage {
  private final SelenideElement registerLink = $(".ico-register");
  private final ElementsCollection headerLinks = $$("div.header-links ul li a");
  private final ElementsCollection topMenu = $$("ul.top-menu li a");
  private final SelenideElement desktopsLink = $(byText("Desktops"));
  private final SelenideElement cartLink = $("a.ico-cart");
  private final SelenideElement cartQuantityBadge = $("a.ico-cart span.cart-qty");

  public RegistrationPage registerButtonClick() {
    registerLink.click();

    return new RegistrationPage();
  }

  public BasePage emailShown(String email) {
    headerLinks.get(0).shouldHave(exactText(email));

    return this;
  }

  public BasePage hoverComputersTab() {
    topMenu.get(1).hover();

    return this;
  }

  public DesktopsPage clickDesktopButton() {
    desktopsLink.click();

    return new DesktopsPage();
  }

  /** Состояние UI - проверка остается в пейдже. exactText, чтобы (3) не совпало с (13). */
  public BasePage checkCartQuantity(String quantity) {
    cartQuantityBadge.shouldHave(exactText("(" + quantity + ")"));

    return this;
  }

  /** Метод уводит на другую страницу - значит возвращает новый пейдж. */
  public ShoppingCartPage openCart() {
    cartLink.click();

    return new ShoppingCartPage();
  }
}
