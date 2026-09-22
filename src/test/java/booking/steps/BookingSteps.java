package booking.steps;

import booking.dto.BookingDto;
import io.qameta.allure.Step;
import net.datafaker.Faker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BookingSteps {
  private static final Faker faker = new Faker();

  @Step("Проверить соответствие всех полей бронирования")
  public static void bookingApiEquals(BookingDto expectedDto, BookingDto actualDto) {
    assertAll("Проверка полей бронирования",
        () -> assertThat(actualDto.getFirstname())
            .as("firstName отличается от ожидаемого")
            .isEqualTo(expectedDto.getFirstname()),
        () -> assertThat(actualDto.getLastname())
            .as("lastName отличается от ожидаемого")
            .isEqualTo(expectedDto.getLastname()),
        () -> assertThat(actualDto.getTotalprice())
            .as("totalPrice отличается от ожидаемого")
            .isEqualTo(expectedDto.getTotalprice()),
        () -> assertThat(actualDto.getDepositpaid())
            .as("depositpaid отличается от ожидаемого")
            .isEqualTo(expectedDto.getDepositpaid()),
        () -> assertThat(actualDto.getAdditionalneeds())
            .as("additionalNeeds отличается от ожидаемого")
            .isEqualTo(expectedDto.getAdditionalneeds()),
        () -> assertThat(actualDto.getBookingdates())
            .as("bookingDates не должен быть null")
            .isNotNull(),
        () -> assertThat(actualDto.getBookingdates().getCheckin())
            .as("checkin отличается от ожидаемого")
            .isEqualTo(expectedDto.getBookingdates().getCheckin()),
        () -> assertThat(actualDto.getBookingdates().getCheckout())
            .as("checkout отличается от ожидаемого")
            .isEqualTo(expectedDto.getBookingdates().getCheckout())
    );
  }

  public static BookingDto createBookingPayload() {
    return BookingDto.builder()
        .firstname("Ivan")
        .lastname("Petrov")
        .totalprice(2000)
        .depositpaid(false)
        .bookingdates(datesWith("2026-01-01", "2027-01-01"))
        .additionalneeds("Wi-fi")
        .build();
  }

  public static BookingDto randomBookingPayload() {
    return BookingDto.builder()
        .firstname(faker.name().firstName())
        .lastname(faker.name().lastName())
        .totalprice(faker.number().numberBetween(100, 1000))
        .depositpaid(faker.bool().bool())
        .bookingdates(datesWith("2026-01-01", "2027-01-01"))
        .additionalneeds(faker.videoGame().title())
        .build();
  }

  public static BookingDto.BookingDates datesWith(String checkin, String checkout) {
    return BookingDto.BookingDates.builder()
        .checkin(checkin)
        .checkout(checkout)
        .build();
  }
}