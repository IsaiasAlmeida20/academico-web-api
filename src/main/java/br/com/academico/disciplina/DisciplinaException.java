package br.com.academico.disciplina;

@SuppressWarnings("serial")
public class DisciplinaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Disciplina";
    }  
    
}

@SuppressWarnings("serial")
class DisciplinaNaoExisteException extends DisciplinaException {
    @Override
    public String getMessage(){
        return "O endereço não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class NomeDisciplinaInvalidoException extends DisciplinaException {
    @Override
    public String getMessage(){
        return "O nome da disciplina é invalido";
    }
}
