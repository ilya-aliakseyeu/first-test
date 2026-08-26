package second_sprint.webshop.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.WsWelcomePage;
import second_sprint.webshop.steps.AuthStep;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;

public class LoginTest extends BaseTest {

  private final AuthStep authStep = new AuthStep();

  @BeforeEach
  void setUp() {
    authStep.register();
    clearBrowserCookies();
    localStorage().clear();
  }

  @Test
  void successLoginTest() {
    open(WebShopConfig.BASE_URL, WsWelcomePage.class)
        .loginButtonClick()
        .inputEmail(authStep.getEmail())
        .inputPassword(authStep.getPassword())
        .clickRememberMe()
        .clickLogInButton()
        .emailShown(authStep.getEmail());
  }
}
