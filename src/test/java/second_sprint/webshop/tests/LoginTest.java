package second_sprint.webshop.tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.config.Config;
import second_sprint.webshop.pages.LoginPage;
import second_sprint.webshop.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;

public class LoginTest extends TestBase {

  private static final Faker faker = new Faker();
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String password = faker.harryPotter().character() + faker.number().numberBetween(5, 10);

  @Test
  public void successLoginTest() {
    open(Config.REGISTRATION_URL, RegistrationPage.class)
            .registration(firstName, lastName, email, password)
            .emailShown(email);

    open(Config.LOGIN_URL, LoginPage.class)
        .inputEmail(email)
        .inputPassword(password)
        .clickRememberMe()
        .clickLogInButton()
        .emailShown(email);
  }
}
