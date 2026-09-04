package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {
  private final SelenideElement registerButton = $(".ico-register");
  private final ElementsCollection headerElements = $$("div.header-links ul li a");
  private final SelenideElement computerTab = $$("ul.top-menu li a").get(1);
  private final SelenideElement desktopButton = $(byText("Desktops"));
  private final SelenideElement cartLink = $("a.ico-cart");

  @Step("Нажать кнопку 'register'")
  public RegistrationPage registerButtonClick() {
    registerButton.click();

    return new RegistrationPage();
  }

  @Step("Проверить отображение электронной почты")
  public BasePage emailShown(String email) {
    headerElements.get(0).shouldHave(text(email));

    return this;
  }

  @Step("Навести на таб 'Computers'")
  public BasePage hoverComputersTab() {
    computerTab.hover();

    return this;
  }

  @Step("Нажать кнопку 'Desktop'")
  public DesktopsPage clickDesktopButton() {
    desktopButton.click();

    return new DesktopsPage();
  }

  @Step("Открыть корзину")
  public ShoppingCartPage openCart() {
    cartLink.click();

    return new ShoppingCartPage();
  }

}