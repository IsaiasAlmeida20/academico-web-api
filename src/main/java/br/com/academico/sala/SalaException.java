package br.com.academico.sala;

public class SalaException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Sala";
    }  
}

class SalaNaoExisteException extends SalaException {
    @Override
    public String getMessage(){
        return "A Sala não existe na base de dados.";
    }
}

class NumeroSalaInvalidoException extends SalaException {
    @Override
    public String getMessage(){
        return "O número da sala é invalido.";
    }
}