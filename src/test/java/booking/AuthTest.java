package booking;

import booking.dto.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.stream.Stream;

import static booking.config.BookingConfig.BOOKING_URL;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthTest extends BaseTest {

  @Test
  void testAuth() {

    AuthResponse response = given()
        .contentType(ContentType.JSON)
        .body(new AuthRequest("admin", "password123"))
        .when()
        .post(BOOKING_URL + "/auth")
        .then()
        .statusCode(200)
        .extract().as(AuthResponse.class);

    assertThat(response.getToken()).isNotNull();
  }


  @ParameterizedTest(name = "{index}) username: ''{0}'', password: ''{1}''")
  @MethodSource("userInvalidCredentials")
  void testInvalidCredentials(String username, String password) {
    ErrorResponse response = given()
        .contentType(ContentType.JSON)
        .body(new AuthRequest(username, password))
        .when()
        .post(BOOKING_URL + "/auth")
        .then()
        .statusCode(200)
        .extract().as(ErrorResponse.class);

    assertThat(response.getReason()).isEqualTo("Bad credentials");

  }

  private static Stream<Arguments> userInvalidCredentials() {
    return Stream.of(
        Arguments.of("admin", "password1234"),
        Arguments.of("invalidAdmin", "password123"),
        Arguments.of("admin", ""),
        Arguments.of("", "password123"),
        Arguments.of(null, null)
    );
  }
}
