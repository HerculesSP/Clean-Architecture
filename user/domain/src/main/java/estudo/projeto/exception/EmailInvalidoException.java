package estudo.projeto.exception;

public class EmailInvalidoException extends DomainException {
    public EmailInvalidoException() {
        super("O formato do e-mail informado é inválido.");
    }

    @Override
    public int getCode() {
        return 400;
    }
}
