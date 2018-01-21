package com.gusuran.rest.web.model.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorResponse {

    private Map<String, List<String>> errors;

    public ErrorResponse(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public ErrorResponse(){
        this(new HashMap<>());
    }

    public void put(String key, String message){
        if(errors.get(key) == null && message != null)
            errors.put(key, new ArrayList<>());

        if(message != null)
            errors.get(key).add(message);
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
