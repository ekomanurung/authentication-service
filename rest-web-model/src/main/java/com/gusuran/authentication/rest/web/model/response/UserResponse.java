package com.gusuran.authentication.rest.web.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse implements Serializable {

  private String username;
  private boolean active;
  private boolean enable;
  private Date lastSuccessLogin;
  private Date lastFailedLogin;
  private int counterFailed;
}
