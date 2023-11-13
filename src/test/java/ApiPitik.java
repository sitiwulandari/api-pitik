import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiPitik {

  public final static String URL = "https://api-dev.pitik.id/";


  @Test
  public void loginPitik() {

    String requestBody = "{\n"
            + "    \"username\": \"siti.wulandari@pitik.id\",\n"
            + "    \"password\": \"12345qa\"\n"
            + "}";

    Response response = given().log().all().baseUri(URL).basePath("/v2")
            .contentType(ContentType.JSON).accept(ContentType.JSON)
            .body(requestBody)
            .post("/auth");

    response.getBody().prettyPrint();
    int statusCode = response.getStatusCode();

    Assert.assertEquals(statusCode, 200);
    System.out.println("The response status is " + statusCode);
  }

  @Test
  public void getSingleUser() {
    Response response = given().log().all().baseUri(URL).basePath("/v2")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6ImVlNGJkMTZlLTVjZTEtNGU5ZC1hY2MzLWM2NDUxNGE1YzIyMCIsInJvbGVJZCI6IjBkZmJiYmVkLWU1ZWMtNDM2MC05ZjM0LTJiNzdkNGNjZDM0NiIsImlhdCI6MTY5OTU4Nzk4MywiZXhwIjoxNjk5Njc0MzgzLCJhdWQiOiJodHRwczovL2FwaS5waXRpay5kZXYvdjIiLCJpc3MiOiJwaXRpayJ9.rEUoILdixYzTKtU5F16agc-KXb7jcmFKfmMlE51p-64")
            .header("X-ID","ee4bd16e-5ce1-4e9d-acc3-c64514a5c220")
            .get("/users");

    response.getBody().prettyPrint();

    int statusCode = response.getStatusCode();
    Assert.assertEquals(statusCode, 200);
    System.out.println("The response status is " + statusCode);
  }



  @Test
  public void updateUser() {

    String requestBodyAdd = "{\n"
        + "    \"name\": \"Tri Abror\",\n"
        + "    \"job\": \"SEIT\"\n"
        + "}";

    String requestBodyUpdate = "{\n"
        + "    \"name\": \"Hendri\",\n"
        + "    \"job\": \"SEIT\"\n"
        + "}";

    Response responseAdd = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .body(requestBodyAdd)
        .post("/users");

    responseAdd.getBody().prettyPrint();

    Response responseUpdate = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .body(requestBodyUpdate)
        .pathParam("userId", responseAdd.path("id"))
        .put("/users/{userId}");

    responseUpdate.getBody().prettyPrint();

    int statusCode = responseUpdate.getStatusCode();
    Assert.assertEquals(statusCode, 200);
    System.out.println("The responseAdd status is " + statusCode);
  }


//  public ResponseApi<CartResponse> getCartItems200(String businessId, String guestId) {
//    Response response = service(CART_SERVICE)
//            .header("business-id", businessId)
//            .header("guest-id", guestId)
//            .get(REQUEST_CART);
//    response.getBody().prettyPrint();
//    return jsonApi.fromJson(response, new TypeReference<CartResponse>() {
//    });


}
