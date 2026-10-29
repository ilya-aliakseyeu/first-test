package booking.ApiClient;

import booking.config.BookingConfig;
import booking.dto.AuthRequest;
import booking.dto.AuthResponse;
import booking.dto.BookingDto;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static booking.config.BookingApiConfig.getBookingConfig;
import static io.restassured.RestAssured.given;

public class BookingApiClient {
  private static final BookingConfig CFG = getBookingConfig();

  private static final RequestSpecification bookingSpec = new RequestSpecBuilder()
      .setBaseUri(CFG.bookingUrl())
      .setContentType(ContentType.JSON)
      .build();

  @Step("Авторизация пользователем {0}")
  public Response auth(String login, String password) {
    return given(bookingSpec)
        .body(new AuthRequest(login, password))
        .when()
        .post("/auth")
        .then()
        .extract().response();
  }

  @Step("Создать бронирование")
  public Response createBooking(BookingDto bookingDto) {
    return given(bookingSpec)
        .body(bookingDto)
        .when()
        .post("/booking")
        .then()
        .extract().response();
  }

  @Step("Обновить бронирование id={1}")
  public Response updateBooking(BookingDto bookingDto, Integer id) {
    return given(bookingSpec)
        .cookie("token", getToken())
        .body(bookingDto)
        .pathParam("BOOKING_ID", id)
        .when()
        .put("/booking/{BOOKING_ID}")
        .then()
        .extract().response();
  }

  @Step("Частично обновить бронирование id={1}")
  public Response partialUpdateBooking(BookingDto bookingDto, Integer id) {
    return given(bookingSpec)
        .cookie("token", getToken())
        .body(bookingDto)
        .pathParam("BOOKING_ID", id)
        .when()
        .patch("/booking/{BOOKING_ID}")
        .then()
        .extract().response();
  }

  @Step("Удалить бронирование id={0}")
  public Response deleteBooking(Integer id) {
    return given(bookingSpec)
        .cookie("token", getToken())
        .pathParam("BOOKING_ID", id)
        .when()
        .delete("/booking/{BOOKING_ID}")
        .then()
        .extract().response();
  }

  @Step("Получить бронирование id={0}")
  public Response getBooking(Integer id) {
    return given(bookingSpec)
        .pathParam("BOOKING_ID", id)
        .when()
        .get("/booking/{BOOKING_ID}")
        .then()
        .extract().response();
  }

  private String getToken() {
    return auth(CFG.bookingUserName(), CFG.bookingPassword())
        .as(AuthResponse.class)
        .getToken();
  }
}