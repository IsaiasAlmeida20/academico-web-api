package br.com.academico.turma;

@SuppressWarnings("serial")
public class TurmaException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Aluno";
    }  
}

@SuppressWarnings("serial")
class TurmaNaoExisteException extends TurmaException {
    @Override
    public String getMessage(){
        return "O Turma não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class NomeTurmaInvalidoException extends TurmaException {
    @Override
    public String getMessage(){
        return "O nome da turma È invalido";
    }
}

