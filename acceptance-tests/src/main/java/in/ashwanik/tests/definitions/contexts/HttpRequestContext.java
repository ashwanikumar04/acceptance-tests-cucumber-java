package in.ashwanik.tests.definitions.contexts;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 */
@Getter
@Setter
@ToString
public class HttpRequestContext {

    private Object request;

    public void updateWith(Object request) {
        this.request = request;
    }
}
