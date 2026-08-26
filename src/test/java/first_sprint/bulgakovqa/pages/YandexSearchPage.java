package first_sprint.bulgakovqa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class YandexSearchPage extends FirstBaseTest {
    private final SelenideElement textInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");

    public YandexSearchResultsPage openStudySection(String textValue) {
        textInput.setValue(textValue);
        submitButton.click();

        return new YandexSearchResultsPage();
    }
}