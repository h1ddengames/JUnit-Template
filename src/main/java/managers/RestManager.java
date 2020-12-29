package managers;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.http.params.CoreConnectionPNames;

public class RestManager {
    public static RestAssuredConfig getConfig() {
        // Timeout is in milliseconds.
        // CONNECTION_TIMEOUT is 'until a connection is established'.
        // SO_TIMEOUT is 'a maximum period inactivity between two consecutive data packets'.
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 100000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 100000));
    }
}
