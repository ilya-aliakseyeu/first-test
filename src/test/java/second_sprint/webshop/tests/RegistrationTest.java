package second_sprint.webshop.tests;

import io.qameta.allure.*;
=import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Epic("Authorization")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {
  private static final Faker faker = new Faker();

  @Test
  @Owner("ilya-alekseev")
  @Tag("positive")
  @Severity(CRITICAL)
  @Link("TASK-120")
  @Story("Регистрация нового пользователя")
  @DisplayName("Успешная регистрация нового пользователя")
  @Description("Создаем нового пользователя через новый интерфейс")
  public void registrationTest() {
    String email = faker.internet().emailAddress();
    String password = faker.harryPotter().character() + faker.number().numberBetween(5, 10);

    open(WebShopConfig.BASE_URL, WsWelcomePage.class)
        .registerButtonClick()
        .chooseMaleGender()
        .inputFirstName(faker.name().firstName())
        .inputLastName(faker.name().lastName())
        .inputEmail(email)
        .inputPassword(password)
        .inputConfirmPassword("password")
        .clickRegisterButton()
        .confirmRegistrationText()
        .emailShown(email);
  }
}
