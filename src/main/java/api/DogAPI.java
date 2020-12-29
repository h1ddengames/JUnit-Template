package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import managers.RestManager;

import static io.restassured.RestAssured.given;

public class DogAPI {
    public static Response getAllDogBreeds() {
        return given()
                .config(RestManager.getConfig())
                //.header("Authorization", "Bearer " + bearerToken)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                //.body()
                .baseUri("https://dog.ceo")
                .basePath("/api/breeds/list/all")
                .get();
    }

    public static Response getRandomDogImage() {
        return given()
                .config(RestManager.getConfig())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri("https://dog.ceo")
                .basePath("/api/breeds/image/random")
                .get();
    }

    public static Response getMultipleRandomDogImages(int amount) {
        if(amount > 50) { return null; }

        return given()
                .config(RestManager.getConfig())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri("https://dog.ceo")
                .basePath("/api/breeds/image/random/" + amount)
                .get();
    }
}
