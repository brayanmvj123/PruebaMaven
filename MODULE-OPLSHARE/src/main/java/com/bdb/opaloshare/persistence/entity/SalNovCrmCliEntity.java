package com.bdb.opaloshare.persistence.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPL_SAL_NOVCRMCLI_DOWN_TBL")
public class SalNovCrmCliEntity implements Serializable {
	
	/**
    *
    */
   private static final long serialVersionUID = 1L;
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ITEM")
   private Long item;
   
   @Column(name = "TIP_ID")
   private String tipId;
   
   @Column(name = "ID_TIT")
   private String idTit;
   
   @Column(name = "TIP_NOVEDAD")
   private int tipNovedad;
   
   @Column(name = "INF_NOVEDAD")
   private String infNovedad;
   
   @Column(name = "STATUS")
   private String status;
  

}
