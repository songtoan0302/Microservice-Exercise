package org.aibles.carservice.service;

import lombok.extern.slf4j.Slf4j;
import org.aibles.carservice.exception.SystemException;
import org.aibles.carservice.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

/**
 * @author ToanNS
 */
@Slf4j
public class BaseService<T, H> {

  private BaseRepository<T, H> baseRepository;

  public <S extends T> S create(S entity) {
    log.info("(create) entity: {}", entity);
    return baseRepository.save(entity);
  }

  public T get(H id) {
    log.info("(get) id: {}", id);
    return baseRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new SystemException("Entity not found! ", HttpStatus.NOT_FOUND);
            });
  }

  public <S extends T> S update(S entity, H id) {
    log.info("(update) entity: {}, id: {} ", entity, id);
    baseRepository
        .findById(id)
        .orElseThrow(
            () -> {
              throw new SystemException("Entity not found!", HttpStatus.NOT_FOUND);
            });
    return baseRepository.save(entity);
  }

  public Page<T> list(Pageable pageable) {
    log.info("(list)");
    return baseRepository.findAll(pageable);
  }

  public void delete(H id) {
    log.info("(delete) id: {}", id);
    baseRepository.deleteById(id);
  }

  public void deleteAll() {
    log.info("(deleteAll)");
    baseRepository.deleteAll();
  }

  //  public List<T> filter(BaseCriteria<T> baseCriteria){
  //    log.info("(filter) baseCriteria: {}", baseCriteria);
  //    return baseRepository.findAll(baseCriteria.toSpecification())
  //  }
}
