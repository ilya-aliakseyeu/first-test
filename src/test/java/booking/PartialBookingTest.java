package booking;

import booking.dto.BookingDto;
import booking.dto.CreateBookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static booking.steps.BookingSteps.datesWith;
import static booking.steps.BookingSteps.randomBookingPayload;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API")})
public class PartialBookingTest extends BaseTest {

  @Test
  @Tag("positive")
  @DisplayName("Частичное обновление полей бронирования (PATCH)")
  public void testPartialUpdateBooking() {
    var createResponse = bookingApiClient.createBooking(randomBookingPayload());
    assertThat(createResponse.getStatusCode()).isEqualTo(200);

    Integer bookingId = createResponse.as(CreateBookingResponse.class).getBookingid();
    createdBookingIds.add(bookingId);

    BookingDto partialUpdateDto = BookingDto.builder()
        .firstname(faker.name().firstName())
        .totalprice(faker.number().numberBetween(2000, 5000))
        .bookingdates(datesWith("2026-02-01", null))
        .build();

    Response partialUpdateResponse = bookingApiClient.partialUpdateBooking(partialUpdateDto, bookingId);
    assertThat(partialUpdateResponse.getStatusCode()).isEqualTo(200);

    BookingDto updatedBookingDto = partialUpdateResponse.as(BookingDto.class);

    assertThat(updatedBookingDto.getFirstname()).isEqualTo(partialUpdateDto.getFirstname());
    assertThat(updatedBookingDto.getTotalprice()).isEqualTo(partialUpdateDto.getTotalprice());
    assertThat(updatedBookingDto.getBookingdates().getCheckin()).isEqualTo(partialUpdateDto.getBookingdates().getCheckin());
  }
}