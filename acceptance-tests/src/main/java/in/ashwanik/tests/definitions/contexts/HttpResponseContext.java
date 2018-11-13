package in.ashwanik.tests.definitions.contexts;

import in.ashwanik.tests.models.RestResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import okhttp3.Headers;

/**
 */
@Getter
@Setter
@ToString
public class HttpResponseContext {

    private int responseCode;
    private String responseBody;
    private Headers headers;

    public void updateWith(RestResponse restResponse) {
        this.responseCode = restResponse.getResponseCode();
        this.responseBody = restResponse.getResponseBody();
        this.headers = restResponse.getHeaders();
    }
}
