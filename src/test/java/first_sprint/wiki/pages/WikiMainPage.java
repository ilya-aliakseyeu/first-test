package first_sprint.wiki.pages;

import com.codeborne.selenide.SelenideElement;
import first_sprint.bulgakovqa.pages.FirstBaseTest;

import static com.codeborne.selenide.Selenide.$;

public class WikiMainPage extends FirstBaseTest {
    private final SelenideElement searchInputField = $("#searchInput");
    private final SelenideElement searchButton = $("[type=submit]");

    public WikiMainPage searchInput(String text) {
        searchInputField.setValue(text);

        return this;
    }

    public WikiArticlePage clickSearchButton() {
        searchButton.click();

        return new WikiArticlePage();
    }
}