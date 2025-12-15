package com.jason.quicktix.comm.base;

import java.util.List;

/** The base mapper interface for converting between entities and DTOs. */
public interface BaseMapper<E, D> {

  /**
   * Convert a DTO to an entity.
   *
   * @param dto the DTO to convert
   * @return the converted entity
   */
  E toEntity(D dto);

  /**
   * Convert an entity to a DTO.
   *
   * @param entity the entity to convert
   * @return the converted DTO
   */
  D toDto(E entity);

  /**
   * Convert a list of entities to a list of DTOs.
   *
   * @param entities the list of entities to convert
   * @return the list of converted DTOs
   */
  List<D> toDtoList(List<E> entities);

  /**
   * Convert a list of DTOs to a list of entities.
   *
   * @param dtos the list of DTOs to convert
   * @return the list of converted entities
   */
  List<E> toEntityList(List<D> dtos);
}
