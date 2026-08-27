package second_sprint.webshop.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class DesktopsPage extends BasePage {
  private final ElementsCollection productLinks = $$("h2.product-title a");

  public ItemPage openProduct(String name) {
    productLinks.findBy(exactText(name)).click();

    return new ItemPage();
  }
}