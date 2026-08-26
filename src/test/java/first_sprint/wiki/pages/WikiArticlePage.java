package first_sprint.wiki.pages;

import com.codeborne.selenide.SelenideElement;
import first_sprint.bulgakovqa.pages.FirstBaseTest;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WikiArticlePage extends FirstBaseTest {
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