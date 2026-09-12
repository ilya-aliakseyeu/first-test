package first_sprint.pages;

import static com.codeborne.selenide.Selenide.*;

public class YandexSearchResultsPage extends BasePage {

    public YandexSearchResultsPage clickSearchResult(String websiteURL) {
        $$("a[href*='" + websiteURL + "']").first().click();

        return this;
    }

}
