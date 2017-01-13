package com.help.server.controller.admincontroller;


import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nesterenko_d on 19.03.2015.
 */

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    Map<String, Object> getErrorMessage(HttpMediaTypeNotSupportedException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("error", "Unsupported Media Type");
        map.put("cause", ex.getLocalizedMessage());
        map.put("supported", ex.getSupportedMediaTypes());
        return map;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Map<String, Object> getErrorMessage(HttpMessageNotReadableException ex) {
        Map<String, Object> map = new HashMap<>();
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause != null) {
            map.put(mostSpecificCause.getClass().getName(), mostSpecificCause.getMessage());
            return map;
        }
        map.put("error", ex.getMessage());
        return map;
    }
}