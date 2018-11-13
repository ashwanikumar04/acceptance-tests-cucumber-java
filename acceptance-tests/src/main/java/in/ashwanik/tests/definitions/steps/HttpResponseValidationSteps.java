package in.ashwanik.tests.definitions.steps;

import cucumber.api.java.en.And;
import in.ashwanik.tests.definitions.contexts.HttpResponseContext;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 */
public class HttpResponseValidationSteps {

    private HttpResponseContext httpResponseContext;

    public HttpResponseValidationSteps(HttpResponseContext httpResponseContext) {
        this.httpResponseContext = httpResponseContext;
    }

    @And("^http response code is (\\d+) .*$")
    public void assertHttpResponseCode(int responseCode) {
        assertThat(httpResponseContext.getResponseCode()).isEqualTo(responseCode);
    }
}
