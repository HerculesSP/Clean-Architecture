package estudo.projeto.exception;

public class PossuiSubordinadosException extends ApplicationException {
    public PossuiSubordinadosException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return 422;
    }
}
