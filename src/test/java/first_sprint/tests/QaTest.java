package first_sprint.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import first_sprint.bulgakovqa.pages.PaymentPage;
import first_sprint.bulgakovqa.pages.WelcomePage;
import first_sprint.wiki.pages.WikiMainPage;
import first_sprint.bulgakovqa.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.open;
import static first_sprint.tests.MentorsConfig.*;

public class QaTest {

    @BeforeAll
    static void setUp() {
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;
    }

    @Test
    void newTest() {

        open(YANDEX_URL, YandexSearchPage.class)
            .openStudySection("bulgakov qa")
            .clickSearchResult(COURSE_HOST)
            .switchToWindow(1, WelcomePage.class)
            .clickWantToBeQaButton()
            .clickRunToPayButton()
            .switchToWindow(2, PaymentPage.class)
            .selectCurrency("RUB")
            .checkPrice("₽ 47 000");
    }

    @Test
    void secondTest() {
        /*
        1. Открываем вики
        2. Ищем в поиске страницу про Селениум
        3. Подтверждаем поиск
        4. Меняем язык на русский
        5. Проверяем на странице наличие фразы: "Selenium WebDriver — инструмент для автоматизации действий веб-браузера."
         */

        open(WIKIPEDIA_URL, WikiMainPage.class)
            .searchInput("Selenium")
            .clickSearchButton()
            .checkTitleContent("Selenium")
            .checkArticleContent("Selenium WebDriver — инструмент для автоматизации действий веб-браузера.");

    }
}