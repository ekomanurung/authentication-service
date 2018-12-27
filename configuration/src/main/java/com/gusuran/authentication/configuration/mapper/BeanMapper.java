package com.gusuran.authentication.configuration.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class BeanMapper {

  public static <S, D> D map(S source, Class<D> clazz) {
    return mapperFacade.map(source, clazz);
  }

  public static <S, C> List<C> mapList(Iterable<S> sources, Class<C> clazz) {
    return mapperFacade.mapAsList(sources, clazz);
  }

  private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

  private static MapperFacade mapperFacade = mapperFactory.getMapperFacade();
}
