package first_sprint.bulgakovqa.pages;

import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends FirstBaseTest {

    public YandexSearchResultsPage clickSearchResult(String websiteURL) {
        $$("a[href*='" + websiteURL + "']").first().click();

        return this;
    }

}