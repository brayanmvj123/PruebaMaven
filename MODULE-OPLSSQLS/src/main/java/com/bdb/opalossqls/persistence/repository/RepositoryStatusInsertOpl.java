package com.bdb.opalossqls.persistence.repository;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bdb.opalossqls.persistence.entity.StatusInsertOpl;
import org.springframework.data.repository.query.Param;

public interface RepositoryStatusInsertOpl extends JpaRepository<StatusInsertOpl, Serializable> {

	@Query("select new StatusInsertOpl(item,estado) from StatusInsertOpl where item = (select max(item) from StatusInsertOpl where estado like :estado ) and fecha= :fecha")
	StatusInsertOpl getMaximoEstado(@Param("fecha") LocalDate fecha, @Param("estado") String estado);

}
