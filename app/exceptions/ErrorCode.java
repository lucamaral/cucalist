package exceptions;

public enum ErrorCode {

    TIPO_DE_CUCA_NAO_IDENTIFICADO("Tipo de cuca não foi identificado"), ORIGEM_DA_CUCA_NAO_IDENTIFICADO("Padaria da cuca não foi identificado");

    private final String mensagem;

    private ErrorCode(final String message) {
        this.mensagem = message;
    }

    public Integer getCodigo() {
        return this.ordinal();
    }

    public String getMensagem() {
        return mensagem;
    }

}
