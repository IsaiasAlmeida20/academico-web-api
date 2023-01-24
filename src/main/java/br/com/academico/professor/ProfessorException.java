package br.com.academico.professor;

@SuppressWarnings("serial")
public class ProfessorException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Aluno";
    }  
}

@SuppressWarnings("serial")
class ProfessorNaoExisteException extends ProfessorException {
    @Override
    public String getMessage(){
        return "O professor não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class MatriculaProfessorInvalidoException extends ProfessorException {
    @Override
    public String getMessage(){
        return "A matricula do professor é invalida";
    }
}
