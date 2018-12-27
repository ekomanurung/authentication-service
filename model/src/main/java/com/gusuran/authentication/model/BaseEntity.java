package com.gusuran.authentication.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {

  @Id
  private String id;

  @Version
  private Long version;

  @CreatedDate
  private Date createdDate;

  @CreatedBy
  private String createdBy;

  @LastModifiedDate
  private Date updatedDate;

  @LastModifiedBy
  private String updatedBy;
}
