package br.com.academico.endereco;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum EnderecoEnum {
    ATIVO,
    DESATIVO;

    public static EnderecoEnum fromString(final String status){
        return EnderecoEnum.valueOf(status);
    }
}
