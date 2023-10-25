package com.bdb.opaloshare.persistence.repository;

import com.bdb.opaloshare.persistence.entity.ConexionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface RepositoryConexiones extends JpaRepository<ConexionesEntity, Serializable> {

	@Query("select new ConexionesEntity(usr.nombreUsuario , usr.contrasena , cone.hostIp , cone.puerto , cone.ruta) "
			+ "from ConexionesEntity cone inner join UserConexEntity usr on usr.idUsuario = cone.oplUserconexTblIdUsuario "
			+ "where cone.idConexion = :identificacionConexion")
	ConexionesEntity parametrosConexion(@Param("identificacionConexion") Integer identificacionConexion);
	
	@Query("select new ConexionesEntity(ruta) from ConexionesEntity where descConex like ('%SUBDIRECTORIO%')")
	List<ConexionesEntity> listaRutas();
	
	@Query("select new ConexionesEntity(ruta) from ConexionesEntity where descConex like ( :carpeta )")
	ConexionesEntity rutaEspecifica(@Param("carpeta") String carpeta);
	
}
