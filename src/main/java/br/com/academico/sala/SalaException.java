package br.com.academico.sala;

@SuppressWarnings("serial")
public class SalaException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Sala";
    }  
}

@SuppressWarnings("serial")
class SalaNaoExisteException extends SalaException {
    @Override
    public String getMessage(){
        return "A Sala não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class NumeroSalaInvalidoException extends SalaException {
    @Override
    public String getMessage(){
        return "O número da sala é invalido.";
    }
}