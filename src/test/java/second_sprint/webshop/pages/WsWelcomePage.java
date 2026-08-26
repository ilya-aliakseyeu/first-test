package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage extends BasePage{
    private static final SelenideElement loginButton = $(".ico-login");

    @Step("Нажать на кнопку 'LogIn'")
    public LoginPage loginButtonClick() {
        loginButton.click();

        return new LoginPage();
    }
}
