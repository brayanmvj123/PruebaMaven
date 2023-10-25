package com.bdb.opalogdoracle.controller.service.implement.renovaflex;

import com.bdb.opalogdoracle.controller.service.interfaces.renovaflex.ValidarMontoMinimoService;
import com.bdb.opaloshare.persistence.entity.HisCondicionCdtsEntity;
import com.bdb.opaloshare.persistence.entity.HisCtrCdtsEntity;
import com.bdb.opaloshare.persistence.entity.SalRenautdigEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@CommonsLog
public class ValidarMontoMinimoImpl implements ValidarMontoMinimoService {

    public ValidarMontoMinimoImpl(){
        // Por el momento constructor vacio.
    }

    @Override
    public boolean montoMinimo(SalRenautdigEntity salRenautdigByNumCdt, HisCtrCdtsEntity hisCtrCdtsEntity, BigDecimal montoMinimo) {
        Optional<HisCondicionCdtsEntity> condicionCapital = hisCtrCdtsEntity.getHisCondicionCdtsEntity()
                .stream()
                .findFirst();

        BigDecimal capital = new BigDecimal(0);
        Integer condicionRendimientos = 0;

        if (condicionCapital.isPresent()) {
            log.info("Se inicia el proceso de validación de condiciones " + salRenautdigByNumCdt.getNumCdt());
            capital = salRenautdigByNumCdt.getCapPg().add(condicionCapital.get().getCapital());
            condicionRendimientos = condicionCapital.get().getRendimientos();
        }

        if (capital.compareTo(montoMinimo) >= 0) {
            log.info("Se valida y solo el capital a reinvertir es mayor al monto minimo "
                    + salRenautdigByNumCdt.getNumCdt());
            return true;
        }

        if (condicionRendimientos == 1) {
            log.info("Se valida si sumando el capital más los intereses son mayores al monto minimo "
                    + salRenautdigByNumCdt.getNumCdt());
            return capital.add(salRenautdigByNumCdt.getIntNeto()).compareTo(montoMinimo) >= 0;
        }

        if (condicionRendimientos == 2) {
            log.info("Se valida y el monto no es mayor al monto minimo, capital a reinvertir: "+ capital + ", " +
                    "intereses : 0, cdt: " + salRenautdigByNumCdt.getNumCdt());
            return false;
        }

        return false;
    }
}
