package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.APIs;
import java.util.List;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Получение списка заказов авторизованным пользователем")
    public static Response getOrdersAuthorizedUser(String accessToken) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .header("Authorization", accessToken)
                .when()
                .get(APIs.ORDERS_PATH);
        return response;
    }

    @Step("Получение списка заказов неавторизованным пользователем")
    public static Response getOrdersUnauthorizedUser() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .get(APIs.ORDERS_PATH);
        return response;
    }

    @Step("Создание заказа авторизованным пользователем")
    public static Response createOrder(String accessToken, Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .header("Authorization", accessToken)
                .and()
                .body(order)
                .when()
                .post(APIs.ORDERS_PATH);
        return response;
    }

    @Step("Создание заказа неавторизованным пользователем")
    public static Response createOrderWithoutAuth(Order order) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(APIs.ORDERS_PATH);
        return response;
    }

    @Step("Получение списка ингридиентов")
    public static List<String> getAllIngredients() {
        List<String> allIngredients = given()
                .header("Content-type", "application/json")
                .when()
                .get(APIs.INGREDIENTS_PATH)
                .then()
                .extract()
                .path("data._id");
        return allIngredients;
    }
}