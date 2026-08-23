package webshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;

public class BaseTest {

  @BeforeAll
  public static void setUp() {
    Configuration.browserSize = "1920x1080";
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  public void after() {
    clearBrowserCookies();
    clearBrowserLocalStorage();
  }
}
