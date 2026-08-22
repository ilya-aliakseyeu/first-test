package ru.bulgakov.mentor.pages.second;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WikiMainPage {
    private final SelenideElement searchInputField = $("#searchInput");
    private final SelenideElement searchButton = $("[type=submit]");

    public WikiMainPage searchInput(String text) {
        searchInputField.setValue(text);
        return this;
    }

    public WikiPage clickSearchButton() {
        searchButton.click();
        return new WikiPage();
    }
}
