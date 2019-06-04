package com.gusuran.authentication.configuration;

import com.gusuran.authentication.model.constant.AuthConstant;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class MongoAuditConfiguration implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return AuthConstant.CREATED_BY;
    }
}
