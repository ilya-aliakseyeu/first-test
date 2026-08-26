package second_sprint.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage extends BasePage{
    private static final SelenideElement loginButton = $(".ico-login");

    public LoginPage loginButtonClick() {
        loginButton.click();

        return new LoginPage();
    }
}
