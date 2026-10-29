package booking;

import booking.dto.BookingDto;
import booking.dto.CreateBookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static booking.steps.BookingSteps.randomBookingPayload;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API")})
public class UpdateBookingTest extends BaseTest {

  @Test
  @Tag("positive")
  @DisplayName("Полное обновление бронирования (PUT)")
  public void testUpdateBooking() {
    BookingDto initialBooking = randomBookingPayload();
    var createResponse = bookingApiClient.createBooking(initialBooking);
    assertThat(createResponse.getStatusCode()).isEqualTo(200);

    Integer bookingId = createResponse.as(CreateBookingResponse.class).getBookingid();
    createdBookingIds.add(bookingId);

    BookingDto updatePayload = randomBookingPayload();
    Response updateResponse = bookingApiClient.updateBooking(updatePayload, bookingId);
    assertThat(updateResponse.getStatusCode()).isEqualTo(200);

    BookingDto updatedBookingDto = updateResponse.as(BookingDto.class);
    assertThat(updatedBookingDto).usingRecursiveComparison().isEqualTo(updatePayload);

    Response getResponse = bookingApiClient.getBooking(bookingId);
    assertThat(getResponse.getStatusCode()).isEqualTo(200);
    assertThat(getResponse.as(BookingDto.class)).usingRecursiveComparison().isEqualTo(updatePayload);
  }
}