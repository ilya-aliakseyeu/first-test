package second_sprint.webshop.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import second_sprint.webshop.config.WebDriverConfig;
import webshop.util.AttachManager;


import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static second_sprint.webshop.config.Config.getChromeOptions;
import static second_sprint.webshop.config.Config.getWebDriverConfig;

public class TestBase {

    private static final WebDriverConfig config = getWebDriverConfig();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = config.browser();

        if ("remote".equals(System.getProperty("run"))) {
            Configuration.remote = "https://user1:1234@selenoid.qa.guru/wd/hub";
            Configuration.browserCapabilities = getChromeOptions();
        }
    }

    @BeforeEach
    void beforekajdiy() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @AfterEach
    void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();

        AttachManager.browserConsoleLogs();
        AttachManager.pageSource();
        AttachManager.takeScreenshot();
        AttachManager.addVideo();
    }
}
