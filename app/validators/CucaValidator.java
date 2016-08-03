package validators;

import exceptions.CucaException;
import exceptions.ErrorCode;
import models.Cuca;

public class CucaValidator {

    private boolean dontHasTipo(final Cuca cuca) {
        return cuca.getTipo() == "";
    }

    private boolean dontHasOrigem(final Cuca cuca) {
        return cuca.getOrigem() == "";
    }

    public void validate(final Cuca cuca) {
        if (dontHasTipo(cuca)) {
            final CucaException exception = new CucaException(ErrorCode.TIPO_DE_CUCA_NAO_IDENTIFICADO);
            throw exception;
        } else if (dontHasOrigem(cuca)) {
            final CucaException exception = new CucaException(ErrorCode.ORIGEM_DA_CUCA_NAO_IDENTIFICADO);
            throw exception;
        }
    }

}
