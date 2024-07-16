package com.sg.sb.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface ServiceConstants {
    String APP_PATH_V1 = "app/v1";
    String ENDPOINT_BOOK = "book";
    String ENDPOINT_BOOK_ALL = "book/all";
    String ENDPOINT_AUTHOR = "author";
    String ENDPOINT_AUTHOR_ALL = "author/all";

    static String marshalJson(Object pojo) {
        String result = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            objectMapper.registerModule(javaTimeModule);

            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        } catch (Exception e) {
            //logger.error(e.getMessage(), e);
        }
        return result;
    }
}
