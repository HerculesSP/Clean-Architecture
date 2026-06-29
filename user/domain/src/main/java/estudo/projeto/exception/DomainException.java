package estudo.projeto.exception;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }

    public abstract int getCode();
}
