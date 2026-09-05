package webshop.util;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class AttachManager {

    @Attachment(value = "Last Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source (HTML)", type = "text/html")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Browser console logs", type = "text/plain")
    public static String browserConsoleLogs() {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            return "WebDriver has not been started yet";
        }

        try {
            List<String> logs = Selenide.getWebDriverLogs(BROWSER);
            if (logs.isEmpty()) {
                return "No browser console logs available";
            }
            return String.join("\n", logs);
        } catch (Exception e) {
            return "Unable to get browser console logs: " + e.getMessage();
        }
    }

    @Attachment(value = "video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' autoplay>"
                + "<source src='" + getVideoUrl() + "' type='video/mp4'>"
                + "</video></body></html>";
    }

    private static URL getVideoUrl() {
        String videoUrl = "https://selenoid.qa.guru/video/" + driver().getSessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e ) {
            e.printStackTrace();
        }
        return null;
    }
}
