package second_sprint.webshop.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  @BeforeAll
  static void setUpBrowser() {
    Configuration.browserSize = "1920x1080";
    Configuration.timeout = 10000;
    Configuration.pageLoadTimeout = 30000;
  }
}
