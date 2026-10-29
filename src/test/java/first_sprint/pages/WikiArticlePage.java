package first_sprint.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WikiArticlePage extends BasePage {
    private final SelenideElement title = $("#firstHeading");
    private final SelenideElement articleContent = $("#mw-content-text");

    public WikiArticlePage checkTitleContent(String expectedTitle) {
        title.shouldHave(text(expectedTitle));

        return this;
    }

    public WikiArticlePage checkArticleContent(String expectedArticle) {
        articleContent.shouldHave(text(expectedArticle));

        return this;
    }
}
