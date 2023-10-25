package com.bdb.opaloshare.persistence.model.trad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.repository.query.Param;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestTradCDT {

    private Long numCdt;
    private String codIsin;
    private Long ctaInv;
}
