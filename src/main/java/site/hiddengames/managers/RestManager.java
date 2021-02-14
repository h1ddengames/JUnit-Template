package site.hiddengames.managers;

import site.hiddengames.annotations.*;

import io.restassured.config.*;
import io.restassured.RestAssured;
import org.apache.http.params.CoreConnectionPNames;

/**
 * Setup configuration details for RestAssured to use.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class RestManager {

    /**
     * Set a timeout for Rest API calls.
     * @return Setup details to set Rest API call timeout to 100 seconds.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
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
