package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonGenerator {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String toJsonString(Object object) {
        JsonGenerator.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        JsonGenerator.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        JsonGenerator.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            return JsonGenerator.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
