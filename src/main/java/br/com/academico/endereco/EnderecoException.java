package br.com.academico.endereco;

@SuppressWarnings("serial")
public class EnderecoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Endereço";
    }  
}

@SuppressWarnings("serial")
class EnderecoNaoExisteException extends EnderecoException {
    @Override
    public String getMessage(){
        return "O endereço não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class CEPEnderecoInvalidoException extends EnderecoException {
    @Override
    public String getMessage(){
        return "O CEP do endereço é inválido.";
    }
}