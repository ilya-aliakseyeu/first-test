package second_sprint.webshop.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

  public static final String BASE_URL = "https://demowebshop.tricentis.com";
  public static final String REGISTRATION_URL = BASE_URL + "/register";
  public static final String LOGIN_URL = BASE_URL + "/login";

  private static final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());

  public static WebDriverConfig getWebDriverConfig() {
    return config;
  }

  public static ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.setCapability("browserVersion", "152.0");

    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("name", "useToastTest");            // имя сессии
    selenoidOptions.put("sessionTimeout", "15m");           // таймаут сессии
    selenoidOptions.put("env", List.of("TZ=UTC"));          // временная зона
    selenoidOptions.put("labels", Map.of("manual", "true")); // кнопка ручного удаления
    selenoidOptions.put("enableVideo", true);               // запись видео
    selenoidOptions.put("enableVNC", true);
    options.setCapability("selenoid:options", selenoidOptions);// просмотр сессии онлайн

    return options;
  }


}
