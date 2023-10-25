package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.OplCarCalendarconDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RepositoryCarCalendarconDownEntity extends JpaRepository<OplCarCalendarconDownEntity, Serializable> {

    List<OplCarCalendarconDownEntity> findByMesGreaterThanEqualAndMesLessThan(int mesInicio, int mesFin);
}
