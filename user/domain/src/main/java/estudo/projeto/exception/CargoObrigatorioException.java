package estudo.projeto.exception;

public class CargoObrigatorioException extends DomainException {
  public CargoObrigatorioException() {
      super("O cargo do usuário é obrigatório e não pode ser nulo.");
  }
}
