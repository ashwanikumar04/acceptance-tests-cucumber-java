package in.ashwanik.tests.definitions.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import in.ashwanik.tests.clients.GreetingClient;
import in.ashwanik.tests.definitions.contexts.HttpResponseContext;
import in.ashwanik.tests.models.RestResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GreetingSteps {
    private HttpResponseContext httpResponseContext;

    @Given("^Greeting service is up$")
    public void greetingServiceIsUp() throws Throwable {
    }

    @When("^I call greeting service$")
    public void iCallGreetingService() throws Throwable {
        RestResponse schemeRestResponse = GreetingClient
                .getInstance()
                .getGreeting();
        httpResponseContext.updateWith(schemeRestResponse);
    }
}
