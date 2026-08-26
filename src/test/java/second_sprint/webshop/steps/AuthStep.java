package second_sprint.webshop.steps;

import net.datafaker.Faker;
import second_sprint.webshop.pages.RegistrationPage;
import second_sprint.webshop.tests.WebShopConfig;

import static com.codeborne.selenide.Selenide.open;

public class AuthStep {
  private static final Faker faker = new Faker();

  private final String firstName = faker.name().firstName();
  private final String lastName = faker.name().lastName();
  private final String email = faker.internet().emailAddress();
  // digits(5) отдает String. randomDigits(5) вернул бы int[], и в пароль уехало бы "[I@1b6d3586".
  private final String password = faker.harryPotter().character() + faker.number().digits(5);

  public AuthStep register() {
    open(WebShopConfig.REGISTRATION_URL, RegistrationPage.class)
        .registration(firstName, lastName, email, password)
        .emailShown(email);

    return this;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
