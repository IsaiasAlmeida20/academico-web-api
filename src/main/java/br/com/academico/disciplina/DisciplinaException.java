package br.com.academico.disciplina;

public class DisciplinaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Disciplina";
    }  
    
}

class DisciplinaNaoExisteException extends DisciplinaException {
    @Override
    public String getMessage(){
        return "O endereço não existe na base de dados.";
    }
}

class NomeDisciplinaInvalidoException extends DisciplinaException {
    @Override
    public String getMessage(){
        return "O nome da disciplina é invalido";
    }
}
