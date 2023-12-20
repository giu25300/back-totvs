package br.com.giuliano.desafio.domain.service;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <D> DTO
 * @param <E> ENTITY
 */
public interface IBaseService<D, E> {
    default ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    /**
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    default D mapToDTO(final E entity, Class<D> outClass) {
        return modelMapper().map(entity, outClass);
    }

    default E mapFromDTO(final D entityDTO, Class<E> outClass) {
        return modelMapper().map(entityDTO, outClass);
    }

    /**
     * @param dto    Object
     * @param entity Object
     * @return entity
     */
    default E mapFromDTO(final D dto, E entity) {

        modelMapper().map(dto, entity);
        return entity;
    }

    /**
     * @param entity Object
     * @param dto    Object
     * @return dto
     */
    default D mapToDTO(final E entity, D dto) {
        modelMapper().map(entity, dto);
        return dto;
    }

    /**
     * <p>
     * Note: outClass object must have default constructor with no arguments
     * </p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @return list of mapped object with <code><D></code> type.
     */
    default List<D> mapAllToDTO(final Iterable<E> entityList, Class<D> outCLass) {
        Collection<E> tList = new ArrayList<>();
        entityList.forEach(tList::add);
        return tList.stream().map(entity -> mapToDTO(entity, outCLass)).collect(Collectors.toList());
    }

    default List<E> mapAllToEntity(final Iterable<D> entityList, Class<E> outCLass) {
        Collection<D> tList = new ArrayList<>();
        entityList.forEach(tList::add);
        return tList.stream().map(entity -> mapFromDTO(entity, outCLass)).collect(Collectors.toList());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source      object to map from
     * @param destination object to map to
     **/
    default <S> D map(final S source, D destination) {
        modelMapper().map(source, destination);
        return destination;
    }
}
