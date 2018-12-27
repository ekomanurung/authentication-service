package com.gusuran.authentication.web.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusinessException extends RuntimeException {

  private String errorCode;
  private String errorMessage;
}
