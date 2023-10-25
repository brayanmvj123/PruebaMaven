package com.bdb.opalo.cofnal.persistance.model.repository;

import com.bdb.opalo.cofnal.persistance.model.entity.CalendarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public interface RepositoryCalendarioCofnal extends JpaRepository<CalendarioEntity, Serializable> {



}
