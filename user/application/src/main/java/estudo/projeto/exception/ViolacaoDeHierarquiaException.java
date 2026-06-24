package estudo.projeto.exception;

public class ViolacaoDeHierarquiaException extends RuntimeException {
    public ViolacaoDeHierarquiaException(String message) {
        super(message);
    }
}
