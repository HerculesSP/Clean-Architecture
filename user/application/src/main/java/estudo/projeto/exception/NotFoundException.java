package estudo.projeto.exception;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 404;
    }
}
