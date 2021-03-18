package com.erkmen.service;

import com.erkmen.domain.BaseEntity;
import com.erkmen.repository.BaseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BaseService<E extends BaseEntity, R extends BaseRepository<E>> {

    private R repository;

    public void save(E entity) {
        repository.save(entity);
    }

    public void delete(E entity) {
        repository.delete(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<E> getAll() {
        return repository.findAll();
    }


}
