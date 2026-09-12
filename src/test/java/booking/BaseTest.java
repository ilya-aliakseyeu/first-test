package booking;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
  @BeforeAll
  static void setUp() {
    RestAssured.filters(
        new RequestLoggingFilter(),
        new ResponseLoggingFilter(),
        new AllureRestAssured());
  }
}
