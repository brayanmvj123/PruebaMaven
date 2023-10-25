package com.bdb.opaloshare.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bdb.opaloshare.persistence.entity.CarDerpatridepDownEntity;

import java.io.Serializable;

@Repository
public interface RepositoryCarDerpatridepDownEntity extends JpaRepository<CarDerpatridepDownEntity, Serializable> {

}
