package user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.APIs;

import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step("Регистрация пользователя")
    public static Response createUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(APIs.REGISTER_PATH);
        return response;
    }

    @Step("Авторизация пользователя")
    public static Response logInUser(User user) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(APIs.LOGIN_PATH);
        return response;
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        if (accessToken != null)
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(APIs.USER_PATH);
    }

    @Step("Изменение авторизованного пользователя")
    public static Response editAuthorizedUser(String accessToken, UserEdit userEditedData) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .header("Authorization", accessToken)
                .and()
                .body(userEditedData)
                .when()
                .patch(APIs.USER_PATH);
        return response;
    }

    @Step("Изменение неавторизованного пользователя")
    public static Response editUnauthorizedUser(String accessToken, UserEdit userEditedData) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(userEditedData)
                .when()
                .patch(APIs.USER_PATH);
        return response;
    }
}