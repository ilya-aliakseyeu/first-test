package ru.bulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage extends BasePage {
    private final SelenideElement textInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");

    public YandexSearchResultsPage openStudySection(String textValue) {
        textInput.setValue(textValue);
        submitButton.click();
        return new YandexSearchResultsPage();
    }
}
