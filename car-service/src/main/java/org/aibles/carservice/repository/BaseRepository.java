package org.aibles.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author ToanNS
 */
@NoRepositoryBean
public interface BaseRepository<T,H> extends JpaRepository<T,H> , JpaSpecificationExecutor<T> {

}
