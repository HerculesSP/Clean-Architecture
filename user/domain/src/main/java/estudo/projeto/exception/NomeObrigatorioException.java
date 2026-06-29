package estudo.projeto.exception;

public class NomeObrigatorioException extends DomainException {
    public NomeObrigatorioException() {
        super("O nome do usuário não pode estar em branco.");
    }

    @Override
    public int getCode() {
        return 400;
    }
}
