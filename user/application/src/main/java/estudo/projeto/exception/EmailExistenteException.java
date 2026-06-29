package estudo.projeto.exception;

public class EmailExistenteException extends ApplicationException {
    public EmailExistenteException() {
        super("Já existe um usuário com esse email cadastrado.");
    }

    @Override
    public int getCode() {
        return 409;
    }
}
