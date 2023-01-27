package br.com.academico.nota;

@SuppressWarnings("serial")
public class NotaException extends RuntimeException{
    
    @Override
    public String getMessage() {
        return "Execeções de Negócio do Modelo de Domínio Nota";
    }  
}

@SuppressWarnings("serial")
class NotaNaoExisteException extends NotaException {
    @Override
    public String getMessage(){
        return "A nota não existe na base de dados.";
    }
}

@SuppressWarnings("serial")
class IdNotaInvalidoException extends NotaException {
    @Override
    public String getMessage(){
        return "O Id da nota é invalido";
    }
}
