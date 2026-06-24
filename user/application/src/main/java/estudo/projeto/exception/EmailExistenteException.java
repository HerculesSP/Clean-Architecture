package estudo.projeto.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException() {
        super("Já existe um usuário com esse email cadastrado.");
    }
}
