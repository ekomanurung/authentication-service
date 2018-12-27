package com.gusuran.authentication.rest.web.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordRequest implements Serializable {

  @NotEmpty
  private String username;

  @NotEmpty
  private String oldPassword;

  @NotEmpty
  private String newPassword;
}
