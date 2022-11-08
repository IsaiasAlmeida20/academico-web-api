package br.com.academico.endereco;

public enum EnderecoEnum {
    ATIVO,
    DESATIVO;

    public static EnderecoEnum fromString(final String status){
        return EnderecoEnum.valueOf(status);
    }
}
