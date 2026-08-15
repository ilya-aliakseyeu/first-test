package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.bulgakov.pages.second.WikiMainPage;
import ru.bulgakov.pages.first.YandexSearchPage;

import static com.codeborne.selenide.Selenide.*;

public class QaTest {

    @Test
    public void newTest() {
        Configuration.pageLoadTimeout = 10000;
        Configuration.timeout = 10000;

        open("https://ya.ru/", YandexSearchPage.class)
                .inputSearchText("bulgakov qa")
                .clickSubmitSearch()
                .clickSearchResult()
                .clickCostButton()
                .clickWantToBeQaButton()
                .clickRunToPayButton()
                .selectCurrency("RUB")
                .checkPrice("₽ 47 000");
    }

    @Test
    public void secondTest() {
        /*
        1. Открываем вики
        2. Ищем в поиске страницу про Селениум
        3. Подтверждаем поиск
        4. Меняем язык на русский
        5. Проверяем на странице наличие фразы: "Selenium WebDriver — инструмент для автоматизации действий веб-браузера."
         */

        open("https://www.wikipedia.org/", WikiMainPage.class)
                .searchInput("Selenium (software)")
                .clickSearchButton()
                .changeLanguage("Русский")
                .checkPageInformation("Selenium WebDriver — инструмент для автоматизации действий веб-браузера.");
    }
}
