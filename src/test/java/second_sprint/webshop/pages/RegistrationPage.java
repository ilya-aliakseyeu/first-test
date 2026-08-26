package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

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

    public RegistrationPage chooseMaleGender() {
        genderRadioButton.click();

        return this;
    }

    public RegistrationPage inputFirstName(String firstName) {
        firstNameField.setValue(firstName);

        return this;
    }

    public RegistrationPage inputLastName(String lastName) {
        lastNameField.setValue(lastName);

        return this;
    }

    public RegistrationPage inputEmail(String email) {
        emailField.setValue(email);

        return this;
    }

    public RegistrationPage inputPassword(String password) {
        passwordField.setValue(password);

        return this;
    }

    public RegistrationPage inputConfirmPassword(String confirmPassword) {
        confirmPasswordField.setValue(confirmPassword);

        return this;
    }

    public ConfirmRegistrationPage clickRegisterButton() {
        $("#register-button").click();

        return new ConfirmRegistrationPage();
    }


}
