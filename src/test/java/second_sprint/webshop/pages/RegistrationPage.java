package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage extends BasePage {
    private static final SelenideElement genderRadioButton = $("#gender-male");
    private static final SelenideElement firstNameField = $("#FirstName");
    private static final SelenideElement lastNameField = $("#LastName");
    private static final SelenideElement emailField = $("#Email");
    private static final SelenideElement passwordField = $("#Password");
    private static final SelenideElement confirmPasswordField = $("#ConfirmPassword");

    public ConfirmRegistrationPage registration(String firstName, String lastName, String email, String password) {
        chooseMaleGender()
            .inputFirstName(firstName)
            .inputLastName(lastName)
            .inputEmail(email)
            .inputPassword(password)
            .inputConfirmPassword(password)
            .clickRegisterButton();

        return new ConfirmRegistrationPage();
    }

    @Step("Указать мужской пол")
    public RegistrationPage chooseMaleGender() {
        genderRadioButton.click();

        return this;
    }

    @Step("Ввести имя")
    public RegistrationPage inputFirstName(String firstName) {
        firstNameField.setValue(firstName);

        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationPage inputLastName(String lastName) {
        lastNameField.setValue(lastName);

        return this;
    }

    @Step("Ввести электронную почту")
    public RegistrationPage inputEmail(String email) {
        emailField.setValue(email);

        return this;
    }

    @Step("Ввести пароль")
    public RegistrationPage inputPassword(String password) {
        passwordField.setValue(password);

        return this;
    }

    @Step("Ввести пароль повторно")
    public RegistrationPage inputConfirmPassword(String confirmPassword) {
        confirmPasswordField.setValue(confirmPassword);

        return this;
    }

    @Step("Нажать кнопку 'register'")
    public ConfirmRegistrationPage clickRegisterButton() {
        $("#register-button").click();

        return new ConfirmRegistrationPage();
    }


}
