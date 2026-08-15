package ru.bulgakov.pages.first;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WelcomePage {

    private final SelenideElement costButton = $$(".t-menu__list li").last();
    private final SelenideElement wantToBeQaButton = $x("//a[@class='tn-atom' and @href='#zeropopup']");
    private final SelenideElement runToPayButton = $(byText("Бегу оплачивать"));

    public WelcomePage clickCostButton() {
        costButton.click();
        return this;
    }

    public WelcomePage clickWantToBeQaButton() {
        wantToBeQaButton.click();
        return this;
    }

    public PaymentPage clickRunToPayButton() {
        runToPayButton.click();
        switchTo().window(2);
        return new PaymentPage();
    }
}
