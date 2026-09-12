package first_sprint.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WikiMainPage extends BasePage {
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
