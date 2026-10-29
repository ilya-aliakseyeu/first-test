package booking;

import booking.dto.AuthResponse;
import booking.dto.ErrorResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Tags({@Tag("API")})
public class AuthTest extends BaseTest {

  @Test
  @Tag("positive")
  @DisplayName("Успешная авторизация с валидными кредами")
  void testAuth() {
    Response resp = bookingApiClient.auth(CFG.bookingUserName(), CFG.bookingPassword());

    assertThat(resp.statusCode()).isEqualTo(200);
    assertThat(resp.as(AuthResponse.class).getToken()).isNotNull();
  }

  @ParameterizedTest(name = "{index}) username: ''{0}'', password: ''{1}''")
  @MethodSource("userInvalidCredentials")
  @Tag("negative")
  @DisplayName("Ошибка авторизации (сервер отвечает 200 и Bad credentials)")
  void testInvalidCredentials(String username, String password) {
    Response resp = bookingApiClient.auth(username, password);

    assertThat(resp.statusCode()).isEqualTo(200);
    assertThat(resp.as(ErrorResponse.class).getReason()).isEqualTo("Bad credentials");
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