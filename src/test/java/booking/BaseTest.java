package booking;

import booking.ApiClient.BookingApiClient;
import booking.config.BookingConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

import static booking.config.BookingApiConfig.getBookingConfig;

public abstract class BaseTest {
  protected BookingApiClient bookingApiClient = new BookingApiClient();
  public static final Faker faker = new Faker();
  public static final BookingConfig CFG = getBookingConfig();

  // Список id созданных сущностей для очистки после каждого теста
  protected final List<Integer> createdBookingIds = new ArrayList<>();

  @BeforeAll
  static void setUp() {
    RestAssured.replaceFiltersWith(
        new AllureRestAssured(),
        new RequestLoggingFilter(),
        new ResponseLoggingFilter()
    );
  }

  @AfterEach
  void tearDown() {
    for (Integer id : createdBookingIds) {
      try {
        bookingApiClient.deleteBooking(id);
      } catch (Exception ignored) {
      }
    }
    createdBookingIds.clear();
  }
}