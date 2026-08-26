package second_sprint.webshop.tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.RegistrationPage;
import second_sprint.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
  private static final Faker faker = new Faker();
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String password = faker.harryPotter().character() + faker.number().numberBetween(5, 10);

  @BeforeEach
  void setUp() {
    open(WebShopConfig.REGISTRATION_URL, RegistrationPage.class)
            .registration(firstName, lastName, email, password)
                .emailShown(email);
    clearBrowserCookies();
    clearBrowserLocalStorage();
  }

  @Test
  public void successLoginTest() {
    open(WebShopConfig.BASE_URL, WsWelcomePage.class)
        .loginButtonClick()
        .inputEmail(email)
        .inputPassword(password)
        .clickRememberMe()
        .clickLogInButton()
        .emailShown(email);
  }
}
