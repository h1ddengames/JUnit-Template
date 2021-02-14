package site.hiddengames.api;

import api.DogAPI;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

@Epic("Dog API")
@Feature("Data with images")
public class DogAPITest {

    @Test
    @Story("User tries to get all dog breeds.")
    @Description("Get the list of all dog breeds.")
    public void getAllDogBreedsTest() {
        DogAPI.getAllDogBreeds().then().assertThat().statusCode(200);
    }

    @Test
    @Story("User tries to get all dog breeds.")
    @Description("Get the list of all dog breeds.")
    public void getRandomDogImageTest() {
        DogAPI.getRandomDogImage().then().assertThat().statusCode(200);
    }

    @Test
    @Story("User tries to get all dog breeds.")
    @Description("Get the list of all dog breeds.")
    public void getMultipleRandomDogImagesTest() {
        DogAPI.getMultipleRandomDogImages(5).then().assertThat().statusCode(200);
    }
}
