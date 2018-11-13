package in.ashwanik.tests.utils;

import in.ashwanik.tests.models.RestResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 */
@Slf4j
public class RestClient {

    private static final RestClient INSTANCE = new RestClient();

    private final OkHttpClient okHttpClient;

    private RestClient() {
        this.okHttpClient = getRestClient();
    }

    public static RestClient getInstance() {
        return INSTANCE;
    }

    public static OkHttpClient getRestClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(50000, TimeUnit.MILLISECONDS)
                .writeTimeout(50000, TimeUnit.MILLISECONDS)
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .build();
    }

    public RestResponse request(String method, String url, Object object) throws Exception {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JsonUtils.toJson(object));
        Request.Builder requestBuilder = new Request.Builder()
                .url(url);

        if (!"GET".equals(method)) {
            requestBuilder.method(method, body);
        }

        try (Response response = okHttpClient.newCall(requestBuilder.build()).execute()) {
            String responseBody = response.body().string();
            int responseCode = response.code();
            log.debug("Response {} ", responseBody);
            return new RestResponse(responseBody, responseCode, response.headers());
        }
    }
}
