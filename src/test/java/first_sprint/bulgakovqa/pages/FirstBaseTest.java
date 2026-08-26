package first_sprint.bulgakovqa.pages;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

public class FirstBaseTest {
  public <T> T switchToWindow(int index, Class<T> pageClass) {
    switchTo().window(index);

    return page(pageClass);
  }
}
