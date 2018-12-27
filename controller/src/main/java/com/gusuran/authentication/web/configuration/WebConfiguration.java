package com.gusuran.authentication.web.configuration;

import com.gusuran.authentication.web.interceptor.MandatoryParameterInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration  extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/docs/v2/api-docs","/v2/api-docs");
        registry.addRedirectViewController("/docs/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/docs/configuration/security","/configuration/security");
        registry.addRedirectViewController("/docs/swagger-resources","/swagger-resources");
        registry.addRedirectViewController("/docs", "/docs/swagger-ui.html");
    }

    @Bean
    public MandatoryParameterInterceptor mandatoryParameterInterceptor(){
        return new MandatoryParameterInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(mandatoryParameterInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/docs/**")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
