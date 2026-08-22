package ru.bulgakov.mentor.pages.first;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage {

    private final SelenideElement searchResult = $("a[href='https://ivanbulgakovqa.ru/']");

    public WelcomePage clickSearchResult() {
        searchResult.click();
        switchTo().window(1);
        return new WelcomePage();
    }

}
