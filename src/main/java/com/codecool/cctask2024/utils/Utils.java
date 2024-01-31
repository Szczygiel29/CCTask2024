package com.codecool.cctask2024.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;

import static com.codecool.cctask2024.exception.ExceptionMessages.JSON_STRING_GENERATING_EXCEPTION;

@Slf4j
public class Utils {

    public static String toJsonString(Object o) {
        var mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String result = null;
        try {
           result = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error(JSON_STRING_GENERATING_EXCEPTION.getMessage());
        }
        return result;
    }
}
