package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class QaTest {

    @Test
    public void newTest() {
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 100000;
        Configuration.browser = "chrome";

        open("https://ya.ru/");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
        $("a[href='https://ivanbulgakovqa.ru/']").click();
        switchTo().window(1);

        $$(".t-menu__list li").last().click();
        $x("//a[@class='tn-atom'and@href='#zeropopup']").click();
        $(byText("Бегу оплачивать")).click();
        switchTo().window(2);
        $("span.ant-select-selection-wrap").click();
        $(byText("RUB")).click();
        $(".styles-module-scss-module__kWKzya__price").shouldHave(text("₽ 47 000 "));
    }

    @Test
    public void secondTest() {
        open("https://www.wikipedia.org/");
        $("#searchInput").setValue("Selenium (software)");
        $("[type=submit]").click();
        $("#p-lang-btn-checkbox").click();
        $x("/html/body/div[8]/div/div[1]/div[1]/div/div/div/input").setValue("Русский");
        $$("#v-0-option-0").first().click();
        $("#mwBQ").shouldBe(text("Selenium WebDriver"));
    }
}
