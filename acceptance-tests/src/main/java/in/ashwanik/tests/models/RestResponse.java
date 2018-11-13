package in.ashwanik.tests.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import okhttp3.Headers;

/**
 */
@AllArgsConstructor
@Getter
@ToString
public class RestResponse {

    private final String responseBody;
    private final int responseCode;
    private final Headers headers;
}
