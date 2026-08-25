package ru.bulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage extends BasePage {
    private final SelenideElement currencyDropdown = $("span.ant-select-selection-wrap");
    private final SelenideElement priceAmount = $x("(//h3[@data-at='H3'])[1]");

    public PaymentPage selectCurrency(String currency) {
        currencyDropdown.click();
        $(byText(currency)).click();
        return this;
    }

    public PaymentPage checkPrice(String price) {
        priceAmount.shouldHave(text(price));
        return this;
    }
}
