package exceptions;

import java.util.ArrayList;
import java.util.List;

public class CucaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;
    private final List<String> informacoes = new ArrayList<>();

    public CucaException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public void adicionarInformacoes(final String informacao) {
        this.informacoes.add(informacao);
    }

    @Override
    public String getMessage() {
        return this.errorCode.getMensagem();
    }

    public List getInformacoes() {
        return this.informacoes;
    }

}
