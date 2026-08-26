package second_sprint.webshop.tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import second_sprint.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationTest {
  private static final Faker faker = new Faker();

  @Test
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
        .inputConfirmPassword(password)
        .clickRegisterButton()
        .confirmRegistrationText()
        .emailShown(email);
  }
}
