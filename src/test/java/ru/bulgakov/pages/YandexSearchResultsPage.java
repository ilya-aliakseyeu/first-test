package ru.bulgakov.pages;

import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends BasePage {

//    private final ElementsCollection searchResult = $$("a[href*='" + websiteURL + "']");

    public YandexSearchResultsPage clickSearchResult(String websiteURL) {
        $$("a[href*='" + websiteURL + "']").first().click();
        return this;
    }

}
