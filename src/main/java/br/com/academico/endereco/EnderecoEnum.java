package br.com.academico.endereco;

public enum EnderecoEnum {
    ATIVADO,
    DESATIVADO;

    public static EnderecoEnum fromString(final String status){
        return EnderecoEnum.valueOf(status);
    }
}
