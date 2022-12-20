package br.com.academico.aluno;

public class AlunoException extends RuntimeException{
    
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Aluno";
    }  
}

class AlunoNaoExisteException extends AlunoException {
    @Override
    public String getMessage(){
        return "O aluno não existe na base de dados.";
    }
}

class MatriculaAlunoInvalidoException extends AlunoException {
    @Override
    public String getMessage(){
        return "A matricula do aluno é invalida";
    }
}
