package booking;

import booking.dto.BookingDto;
import booking.dto.CreateBookingResponse;
import booking.steps.BookingSteps;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static booking.steps.BookingSteps.createBookingPayload;
import static booking.steps.BookingSteps.datesWith;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API")})
public class CreateBookingTest extends BaseTest {

  @Test
  @Tag("positive")
  @DisplayName("Успешное создание бронирования и верификация через GET")
  void testCreateBooking() {
    BookingDto requestDto = createBookingPayload();
    Response resp = bookingApiClient.createBooking(requestDto);
    assertThat(resp.statusCode()).isEqualTo(200);

    CreateBookingResponse responseDto = resp.as(CreateBookingResponse.class);
    assertThat(responseDto.getBookingid()).isNotNull().isPositive();
    createdBookingIds.add(responseDto.getBookingid());

    BookingSteps.bookingApiEquals(requestDto, responseDto.getBooking());

    // Проверяем факт сохранения в БД через GET
    Response getResponse = bookingApiClient.getBooking(responseDto.getBookingid());
    assertThat(getResponse.statusCode()).isEqualTo(200);
    assertThat(getResponse.as(BookingDto.class))
        .usingRecursiveComparison()
        .isEqualTo(requestDto);
  }

  @ParameterizedTest(name = "{index}) Пропуск обязательного поля: {0}")
  @MethodSource("missingRequiredFieldsProvider")
  @Tag("negative")
  @DisplayName("Создание бронирования с отсутствующими обязательными полями")
  void testMissingRequiredFieldsCreateBooking(String testName, BookingDto payload) {
    Response resp = bookingApiClient.createBooking(payload);

    assertThat(resp.getStatusCode()).isEqualTo(500);
    assertThat(resp.body().asString()).doesNotContain("bookingid");
  }

  @Test
  @Tag("negative")
  @Issue("BOOKER-12")
  @DisplayName("Баг: Отрицательный totalprice принимается сервером со статусом 200")
  void testNegativePriceCreateBooking() {
    BookingDto request = createBookingPayload().toBuilder().totalprice(-500).build();
    Response response = bookingApiClient.createBooking(request);

    assertThat(response.statusCode()).isEqualTo(200);
    CreateBookingResponse responseDto = response.as(CreateBookingResponse.class);
    assertThat(responseDto.getBookingid()).isNotNull().isPositive();
    createdBookingIds.add(responseDto.getBookingid());

    assertThat(responseDto.getBooking().getTotalprice()).isEqualTo(-500);
  }

  @Test
  @Tag("negative")
  @Issue("BOOKER-13")
  @DisplayName("Баг: Невалидный формат даты checkin возвращает '0NaN-aN-aN' и 200")
  void testInvalidDateFormatCreateBooking() {
    BookingDto request = createBookingPayload().toBuilder()
        .bookingdates(datesWith("xxx", "2027-01-01"))
        .build();
    Response resp = bookingApiClient.createBooking(request);

    assertThat(resp.statusCode()).isEqualTo(200);
    CreateBookingResponse responseDto = resp.as(CreateBookingResponse.class);
    assertThat(responseDto.getBookingid()).isNotNull().isPositive();
    createdBookingIds.add(responseDto.getBookingid());

    assertThat(responseDto.getBooking().getBookingdates().getCheckin()).isEqualTo("0NaN-aN-aN");
  }

  @Test
  @Tag("negative")
  @Issue("BOOKER-14")
  @DisplayName("Баг: Дата выезда раньше даты заезда принимается со статусом 200")
  void testInvalidDateRangeCreateBooking() {
    BookingDto request = createBookingPayload().toBuilder()
        .bookingdates(datesWith("2027-01-01", "2026-01-01"))
        .build();
    Response resp = bookingApiClient.createBooking(request);

    assertThat(resp.statusCode()).isEqualTo(200);
    CreateBookingResponse responseDto = resp.as(CreateBookingResponse.class);
    assertThat(responseDto.getBookingid()).isNotNull().isPositive();
    createdBookingIds.add(responseDto.getBookingid());

    assertThat(responseDto.getBooking().getBookingdates().getCheckin()).isEqualTo("2027-01-01");
    assertThat(responseDto.getBooking().getBookingdates().getCheckout()).isEqualTo("2026-01-01");
  }

  private static Stream<Arguments> missingRequiredFieldsProvider() {
    return Stream.of(
        Arguments.of("firstname", createBookingPayload().toBuilder().firstname(null).build()),
        Arguments.of("lastname", createBookingPayload().toBuilder().lastname(null).build()),
        Arguments.of("empty body", BookingDto.builder().build())
    );
  }
}