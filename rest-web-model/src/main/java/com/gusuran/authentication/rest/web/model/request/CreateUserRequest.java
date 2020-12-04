package com.gusuran.authentication.rest.web.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequest implements Serializable {

  @NotBlank(message = "username must not be blank")
  private String username;

  @NotBlank(message = "password must not be blank")
  private String password;
}
