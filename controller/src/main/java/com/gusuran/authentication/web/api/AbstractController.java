package com.gusuran.authentication.web.api;

import com.gusuran.authentication.rest.web.model.base.Response;
import com.gusuran.authentication.rest.web.model.request.MandatoryParameterRequest;
import com.gusuran.authentication.web.interceptor.MandatoryParameterInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Please extends this class while creating Controller
 */

public class AbstractController {

  /**
   * Get Mandatory parameter from http servlet request
   *
   * @param request http servlet request
   * @return mandatory parameter or null
   */

  @ModelAttribute
  public MandatoryParameterRequest getMandatoryParameter(HttpServletRequest request) {
    return (MandatoryParameterRequest) request.getAttribute(MandatoryParameterInterceptor.PARAMETER);
  }

  public <T> Response<T> toResponse(T data) {
    return Response.<T>builder()
        .code(HttpStatus.OK.value())
        .message(HttpStatus.OK.toString())
        .data(data)
        .build();
  }
}
