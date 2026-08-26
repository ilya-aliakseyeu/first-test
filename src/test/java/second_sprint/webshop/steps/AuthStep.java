package second_sprint.webshop.steps;

import net.datafaker.Faker;
import second_sprint.webshop.tests.WebShopConfig;
import second_sprint.webshop.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthStep {
  private static final Faker faker = new Faker();
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String password = faker.harryPotter().character() + faker.number().randomDigits(5);

  public void register() {
    open(WebShopConfig.REGISTRATION_URL, RegistrationPage.class)
        .registration(firstName, lastName, email, password)
        .emailShown(email);
  }
}
