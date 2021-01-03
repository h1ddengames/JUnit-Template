package api;

import annotations.*;
import managers.RestManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Uses https://dog.ceo to provide dog facts and images.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "11/12/2020",
        currentRevision = 2, lastModified = "11/14/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"DogAPITest"})
public class DogAPI {
    /**
     * API call to https://dog.ceo/api/breeds/list/all to get dog breeds as JSON.
     * @return A RestAssured API Response containing a status code + JSON data.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"DogAPITest"})
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

    /**
     * API call to https://dog.ceo/api/breeds/image/random to get an image of a random dog.
     * @return A RestAssured API Response containing a status code + an image.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"DogAPITest"})
    public static Response getRandomDogImage() {
        return given()
                .config(RestManager.getConfig())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .baseUri("https://dog.ceo")
                .basePath("/api/breeds/image/random")
                .get();
    }

    /**
     * API call to https://dog.ceo/api/breeds/image/random to get multiple images of random dogs.
     * @param amount The amount of images to download. There is a hard limit of 50 provided by the API creator.
     * @return A RestAssured API Response containing a status code + multiple images.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v1.0.0", consumers = {"DogAPITest"})
    public static Response getMultipleRandomDogImages(int amount) {
        // Hard limit provided by the API creator.
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
