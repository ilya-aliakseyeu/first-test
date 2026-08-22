package ru.bulgakov.mentor.pages.second;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WikiPage {
    private final SelenideElement languagesListButton = $("#p-lang-btn-checkbox");
    private final SelenideElement languagesSearchInput = $x("//input[@role='combobox']");
    private final SelenideElement infoElement = $("#mwBA");


    public WikiPage changeLanguage(String language) {
        languagesListButton.click();
        languagesSearchInput.setValue(language);
        $$("#v-0-option-0").first().click();
        return this;
    }

    public WikiPage checkPageInformation(String infoText) {
        infoElement.shouldHave(text(infoText));
        return this;
    }
}
