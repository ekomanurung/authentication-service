package com.gusuran.authentication.web.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class ErrorHelper {

  @Autowired
  private MessageSource messageSource;

  public Map<String, List<String>> from(BindingResult result) {
    return from(result, messageSource, Locale.getDefault());
  }

  public Map<String, List<String>> from(BindingResult result,
      MessageSource messageSource,
      Locale locale) {
    if (result.hasFieldErrors()) {
      Map<String, List<String>> map = new HashMap<>();
      Iterator var4 = result.getFieldErrors().iterator();

      while (var4.hasNext()) {
        FieldError fieldError = (FieldError) var4.next();
        String field = fieldError.getField();
        if (!map.containsKey(fieldError.getField())) {
          map.put(field, new ArrayList<>());
        }

        String errorMessage =
            messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(),
                fieldError.getDefaultMessage(), locale);
        ((List) map.get(field)).add(errorMessage);
      }
      return map;
    } else {
      return Collections.emptyMap();
    }
  }
}
