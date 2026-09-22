package booking;

import booking.dto.CreateBookingResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static booking.steps.BookingSteps.createBookingPayload;
import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API")})
public class DeleteBookingTest extends BaseTest {

  @Test
  @Tag("positive")
  @DisplayName("Удаление существующего бронирования (статус 201) и проверка недоступности (404)")
  void testDeleteBooking() {
    Response resp = bookingApiClient.createBooking(createBookingPayload());
    assertThat(resp.statusCode()).isEqualTo(200);

    Integer bookingId = resp.as(CreateBookingResponse.class).getBookingid();

    Response deleteResponse = bookingApiClient.deleteBooking(bookingId);
    assertThat(deleteResponse.statusCode()).isEqualTo(201);
    assertThat(bookingApiClient.getBooking(bookingId).statusCode()).isEqualTo(404);
  }
}