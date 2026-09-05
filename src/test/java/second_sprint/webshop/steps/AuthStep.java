package second_sprint.webshop.steps;

import net.datafaker.Faker;
import second_sprint.webshop.config.Config;
import second_sprint.webshop.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthStep {
  private static final Faker faker = new Faker();

  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  private final String password = faker.harryPotter().character() + faker.number().digits(5);

  public void register() {
    open(Config.REGISTRATION_URL, RegistrationPage.class)
        .registration(firstName, lastName, email, password)
        .emailShown(email);
  }
}
