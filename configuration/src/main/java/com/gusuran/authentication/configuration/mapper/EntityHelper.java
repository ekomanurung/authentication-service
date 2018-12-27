package com.gusuran.authentication.configuration.mapper;

import com.gusuran.authentication.model.BaseEntity;

public class EntityHelper {
  public static void map(
      BaseEntity source,
      BaseEntity destination) {

    destination.setId(source.getId());
    destination.setVersion(source.getVersion());
    destination.setCreatedDate(source.getCreatedDate());
    destination.setCreatedBy(source.getCreatedBy());
    destination.setUpdatedDate(source.getUpdatedDate());
    destination.setUpdatedBy(source.getUpdatedBy());
  }
}
