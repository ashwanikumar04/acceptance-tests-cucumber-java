package in.ashwanik.tests.clients;

import in.ashwanik.tests.models.RestResponse;
import in.ashwanik.tests.utils.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;

@Slf4j
public class GreetingClient {
    private static GreetingClient INSTANCE = new GreetingClient();

    private GreetingClient() {
    }

    public static GreetingClient getInstance() {
        return INSTANCE;
    }

    public RestResponse getGreeting() {

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .addPathSegments("/greet");

        try {
            return RestClient.getInstance().request("GET", urlBuilder.toString(), new Object());
        } catch (Exception e) {
            log.debug("Error occurred while getting balance {}", e);
        }
        return null;
    }

}
