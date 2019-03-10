package com.gusuran.authentication.model.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.karneim.pojobuilder.GeneratePojoBuilder;

@Data
@NoArgsConstructor
@GeneratePojoBuilder
public class BusinessException extends RuntimeException {

    private int errorCode;
    private String errorMessage;
}
