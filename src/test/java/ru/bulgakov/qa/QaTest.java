package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QaTest {

    @Test
    public void newTest() {
        Configuration.timeout = 100000;

        open("https://ya.ru/");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
        $("a[href='https://ivanbulgakovqa.ru/']").click();
        switchTo().window(1);

        $$(".t-menu__list li").last().click();
        $x("//a[@class='tn-atom' and @href='#zeropopup']").click();
        $(byText("Бегу оплачивать")).click();
        switchTo().window(2);
        $("span.ant-select-selection-wrap").click();
        $(byText("RUB")).click();
        $(".styles-module-scss-module__kWKzya__price").shouldHave(text("₽ 47 000 "));
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

        open("https://www.wikipedia.org/");
        $("#searchInput").setValue("Selenium (software)");
        $("[type=submit]").click();
        $("#p-lang-btn-checkbox").click();
        $x("//input[@role='combobox']").setValue("Русский");
        $$("#v-0-option-0").first().click();
        $("#mwBA").shouldHave(text("Selenium WebDriver — инструмент для автоматизации действий веб-браузера."));
    }
}
