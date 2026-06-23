package estudo.projeto.exception;

public class StrategyInexistenteException extends RuntimeException {
    public StrategyInexistenteException() {
        super("Nenhuma estratégia encontrada para esse cargo.");
    }
}
