package webshop.tests;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import webshop.TestData;
import webshop.pages.LoginPage;
import webshop.pages.RegistrationPage;
import webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static webshop.TestData.BASE_URL;
import static webshop.TestData.LOGIN_URL;

public class LoginTest {
  private static final Faker faker = new Faker();
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String password = faker.harryPotter().character() + faker.number().numberBetween(5, 10);

  @Nested
  public class PositiveTest {
    @BeforeEach
    void setUp() {
      open(TestData.REGISTRATION_URL, RegistrationPage.class)
          .registration(firstName, lastName, email, password)
          .emailShown(email);
    }

    @Test
    @DisplayName("Успешный логин")
    @Severity(CRITICAL)
    @Owner("ilya-alekseev")
    @Link(name = "TASK-229")
    public void successLoginTest() {
      open(BASE_URL, WsWelcomePage.class)
          .loginButtonClick()
          .inputEmail(email)
          .inputPassword(password)
          .clickRememberMe()
          .clickLogInButton()
          .emailShown(email);
    }
  }

  @ParameterizedTest
  @DisplayName("Отображение валидации email")
  @Severity(SeverityLevel.NORMAL)
  @Owner("ilya-alekseev")
  @Link(name = "TASK-230")
  @CsvFileSource(resources = "/email.csv")
  public void invalidEmailTest(String email) {
    open(LOGIN_URL, LoginPage.class)
        .inputEmail(email)
        .inputPassword("password")
        .validationEmailErrorAppear();
  }
}
