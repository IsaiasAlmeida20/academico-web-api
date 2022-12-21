package br.com.academico.turma;

public class TurmaException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Aluno";
    }  
}

class TurmaNaoExisteException extends TurmaException {
    @Override
    public String getMessage(){
        return "O Turma não existe na base de dados.";
    }
}

class NomeTurmaInvalidoException extends TurmaException {
    @Override
    public String getMessage(){
        return "O nome da turma È invalido";
    }
}

