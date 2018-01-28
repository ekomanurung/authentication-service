package com.gusuran.main.web.interceptor;

import com.gusuran.rest.web.model.MandatoryParameterRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MandatoryParameterInterceptor extends HandlerInterceptorAdapter {

    public static final String PARAMETER = "mandatory";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        MandatoryParameterRequest mandatoryParameter = getMandatoryParameter(request);
        request.setAttribute(PARAMETER, mandatoryParameter);

        return true;
    }

    /**
     * Get Mandatory parameter from httpservlet request
     *
     * @param request servlet
     * @return mandatory param
     */
    protected MandatoryParameterRequest getMandatoryParameter(HttpServletRequest request) {
        return MandatoryParameterRequest.builder()
                .channelId(request.getHeader("channelId"))
                .requestId(request.getHeader("requestId"))
                .username(request.getHeader("username"))
                .clientId(request.getHeader("clientId"))
                .build();
    }
}