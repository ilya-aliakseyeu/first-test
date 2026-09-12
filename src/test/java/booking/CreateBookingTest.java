package booking;

import booking.dto.CreateBookingDto;
import booking.dto.CreateBookingResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static booking.config.BookingConfig.BOOKING_URL;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateBookingTest extends BaseTest {

  @Test
  void testCreateBooking() {

    CreateBookingResponse response = given()
        .contentType(ContentType.JSON)
        .body(createBooking())
        .when()
        .post(BOOKING_URL + "/booking")
        .then()
        .statusCode(200)
        .extract().as(CreateBookingResponse.class);

    assertThat(response.getBookingid()).isNotNull();
    assertThat(response.getBooking().getTotalprice()).isEqualTo(2000);
    assertThat(response.getBooking().getAdditionalneeds()).isEqualTo("Wi-fi");
  }


  @ParameterizedTest(name = "{index}) {0}")
  @MethodSource("missingRequiredFieldsProvider")
  void testMissingRequiredFieldsCreateBooking(String testName, CreateBookingDto payload) {
    given()
        .contentType(ContentType.JSON)
        .body(payload)
        .when()
        .post(BOOKING_URL + "/booking")
        .then()
        .statusCode(500);
  }

  @Test
  void testNegativePriceCreateBooking() {
    CreateBookingResponse response = given()
        .contentType(ContentType.JSON)
        .body(createBookingNegativePrice())
        .when()
        .post(BOOKING_URL + "/booking")
        .then()
        .statusCode(200)
        .extract().as(CreateBookingResponse.class);

    assertThat(response.getBookingid()).isNotNull();
    assertThat(response.getBooking().getTotalprice()).isEqualTo(-500);
    // Тут должны были получить 400 статус-код и ошибку ErrorResponse с reason "'totalprice' cannot be negative"
  }

  @Test
  void testInvalidDataFormatCreateBooking() {
    CreateBookingResponse response = given()
        .contentType(ContentType.JSON)
        .body(createBookingInvalidDataFormat())
        .when()
        .post(BOOKING_URL + "/booking")
        .then()
        .statusCode(200)
        .extract().as(CreateBookingResponse.class);

    assertThat(response.getBooking().getBookingdates().getCheckin()).isEqualTo("0NaN-aN-aN");
    // Тут должны были получить 400 статус-код и ошибку ErrorResponse с reason "'bookingdates.checkind' should have format 'yyyy-mm-dd-"
    // и почему-то bookingid не вернули :c
  }

  @Test
  void testInvalidDataRangeCreateBooking() {
    CreateBookingResponse response = given()
        .contentType(ContentType.JSON)
        .body(createBookingWithInvalidDateRange())
        .when()
        .post(BOOKING_URL + "/booking")
        .then()
        .statusCode(200)
        .extract().as(CreateBookingResponse.class);

    assertThat(response.getBookingid()).isNotNull();
    assertThat(response.getBooking().getBookingdates().getCheckin()).isEqualTo("2027-01-01");
    assertThat(response.getBooking().getBookingdates().getCheckout()).isEqualTo("2026-01-01");
    // Тут должны были получить 400 статус-код и ошибку ErrorResponse с reason "'bookingdates.checkout' must be after 'bookingdates.checkin'"
  }

  private static Stream<Arguments> missingRequiredFieldsProvider() {
    return Stream.of(
        Arguments.of("firstname", createBooking().toBuilder().firstname(null).build()),
        Arguments.of("lastname", createBooking().toBuilder().lastname(null).build()),
        Arguments.of("empty body", CreateBookingDto.builder().build())
    );
  }

  private static CreateBookingDto createBookingWithInvalidDateRange() {
    return CreateBookingDto.builder()
        .firstname("Ivan")
        .lastname("Petrov")
        .totalprice(500)
        .depositpaid(false)
        .bookingdates(CreateBookingDto.BookingDates.builder()
            .checkin("2027-01-01")
            .checkout("2026-01-01")
            .build())
        .additionalneeds("Wi-fi")
        .build();
  }

  private static CreateBookingDto createBookingInvalidDataFormat() {
    return CreateBookingDto.builder()
        .firstname("Ivan")
        .lastname("Petrov")
        .totalprice(500)
        .depositpaid(false)
        .bookingdates(CreateBookingDto.BookingDates.builder()
            .checkin("xxx")
            .checkout("2027-01-01")
            .build())
        .additionalneeds("Wi-fi")
        .build();
  }

  private static CreateBookingDto createBookingNegativePrice() {
    return CreateBookingDto.builder()
        .firstname("Ivan")
        .lastname("Petrov")
        .totalprice(-500)
        .depositpaid(false)
        .bookingdates(CreateBookingDto.BookingDates.builder()
            .checkin("2026-01-01")
            .checkout("2027-01-01")
            .build())
        .additionalneeds("Wi-fi")
        .build();
  }

  private static CreateBookingDto createBooking() {
    return CreateBookingDto.builder()
        .firstname("Ivan")
        .lastname("Petrov")
        .totalprice(2000)
        .depositpaid(false)
        .bookingdates(CreateBookingDto.BookingDates.builder()
            .checkin("2026-01-01")
            .checkout("2027-01-01")
            .build())
        .additionalneeds("Wi-fi")
        .build();
  }
}


