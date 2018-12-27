package com.gusuran.authentication.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "user")
public class User extends BaseEntity implements Serializable {

  private String username;
  private String password;
  private boolean active;
  private boolean enable;
  private RegistrationMethod registrationMethod;
  private Date lastSuccessLogin;
  private Date lastFailedLogin;
  private int counterFailed;
}
