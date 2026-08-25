package ru.bulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage extends BasePage {

    private final SelenideElement costButton = $$(".t-menu__list li").last();
    private final SelenideElement wantToBeQaButton = $x("//a[@class='tn-atom' and @href='#zeropopup']");
    private final SelenideElement runToPayButton = $(byText("Бегу оплачивать"));

    public WelcomePage clickWantToBeQaButton() {
        costButton.click();
        wantToBeQaButton.click();
        return this;
    }

    public PaymentPage clickRunToPayButton() {
        runToPayButton.click();
        return new PaymentPage();
    }
}
