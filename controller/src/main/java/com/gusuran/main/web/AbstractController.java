package com.gusuran.main.web;

import com.gusuran.main.interceptor.MandatoryParameterInterceptor;
import com.gusuran.rest.web.model.MandatoryParameterRequest;
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
}
