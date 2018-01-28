package com.gusuran.main.web.error;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.*;

public class Errors {

    public Errors(){

    }

    public static Map<String, List<String>> from(BindingResult result, MessageSource messageSource){
        return from(result, messageSource, Locale.getDefault());
    }

    public static Map<String, List<String>> from(BindingResult result, MessageSource messageSource, Locale locale){
        if(result.hasFieldErrors()){
            Map<String, List<String>> map = new HashMap<>();
            Iterator var4 = result.getFieldErrors().iterator();

            while (var4.hasNext()){
                FieldError fieldError = (FieldError) var4.next();
                String field = fieldError.getField();
                if(!map.containsKey(fieldError.getField())){
                    map.put(field, new ArrayList<>());
                }

                String errorMessage = messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(),
                        fieldError.getDefaultMessage(), locale);
                ((List)map.get(field)).add(errorMessage);
            }
            return map;
        }else {
            return Collections.emptyMap();
        }
    }
}
