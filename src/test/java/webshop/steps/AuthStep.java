package webshop.steps;

import net.datafaker.Faker;
import webshop.TestData;
import webshop.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;

public class AuthStep {
  private static final Faker faker = new Faker();
  String firstName = faker.name().firstName();
  String lastName = faker.name().lastName();
  String email = faker.internet().emailAddress();
  String password = faker.harryPotter().character() + faker.number().randomDigits(5);

  public void register() {
    open(TestData.REGISTRATION_URL, RegistrationPage.class)
        .registration(firstName, lastName, email, password)
        .emailShown(email);
  }
}
