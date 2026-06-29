package estudo.projeto.exception;

public class StrategyInexistenteException extends ApplicationException {
    public StrategyInexistenteException() {
        super("Não é possível apagar um usuário com este cargo.");
    }

    @Override
    public int getCode() {
        return 422;
    }
}
