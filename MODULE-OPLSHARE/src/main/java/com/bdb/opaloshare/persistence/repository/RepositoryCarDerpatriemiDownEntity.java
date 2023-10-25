package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.CarDerpatriemiDownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RepositoryCarDerpatriemiDownEntity extends JpaRepository<CarDerpatriemiDownEntity, Serializable> {
}
