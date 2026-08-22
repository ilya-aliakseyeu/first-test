package ru.bulgakov.mentor.pages.first;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage {

    public PaymentPage selectCurrency(String currency) {
        $("span.ant-select-selection-wrap").click();
        $(byText(currency)).click();
        return this;
    }

    public PaymentPage checkPrice(String price) {
        $x("(//h3[@data-at='H3'])[1]").shouldHave(text(price));
        return this;
    }
}
