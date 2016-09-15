package validators;

import exceptions.CucaException;
import exceptions.ErrorCode;
import models.Evento;

public class EventoValidator {

    private boolean hasTitulo(final Evento evento) {
        return evento.getTitulo() != "";
    }

    private boolean hasDescricao(final Evento evento) {
        return evento.getDescricao() != "";
    }

    private boolean hasPrazo(final Evento evento) {
        return evento.getPrazo() != null;
    }

    public void validate(final Evento evento) {
        if (!hasTitulo(evento)) {
            final CucaException exception = new CucaException(ErrorCode.TITULO_DO_EVENTO_NAO_IDENTIFICADO);
            throw exception;
        } else if (!hasDescricao(evento)) {
            final CucaException exception = new CucaException(ErrorCode.DESCRICAO_DO_EVENTO_NAO_IDENTIFICADO);
            throw exception;
        } else if (!hasPrazo(evento)) {
            final CucaException exception = new CucaException(ErrorCode.PRAZO_DO_EVENTO_NAO_IDENTIFICADO);
            throw exception;
        }
    }

}
