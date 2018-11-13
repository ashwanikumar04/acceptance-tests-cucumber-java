package in.ashwanik.tests.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * This class contains utility methods for json and Couchbase document creation
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper OBJECT_MAPPER = null;

    private static ObjectMapper getObjectMapper() {
        if (OBJECT_MAPPER == null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            OBJECT_MAPPER = mapper;
        }
        return OBJECT_MAPPER;
    }

    /**
     * Converts an object to its json string
     *
     * @param data Object
     * @return JSON string
     */
    public static <T> String toJson(T data) throws JsonProcessingException {
        if (data == null) {
            return "";
        }
        return getObjectMapper().writeValueAsString(data);
    }

    /**
     * Converts a json string to its POJO
     *
     * @param json  JSON String
     * @param clazz Class type of the POJO
     * @return POJO
     */
    public static <T> T fromJsonToObject(String json, Class<T> clazz) throws IOException {
        return getObjectMapper().readValue(json, clazz);
    }

    /**
     * Converts String to Object
     *
     * @param json          String
     * @param typeReference Type reference
     * @return
     */
    public static <T> T fromJsonToObject(String json, TypeReference<T> typeReference) throws IOException {
        return getObjectMapper().readValue(json, typeReference);
    }
}
