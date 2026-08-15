package ru.bulgakov.pages.first;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage {
    private final SelenideElement textInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");

    public YandexSearchPage inputSearchText(String textValue) {
        textInput.setValue(textValue);
        return this;
    }

    public YandexSearchResultsPage clickSubmitSearch() {
        submitButton.click();
        return new YandexSearchResultsPage();
    }

}
