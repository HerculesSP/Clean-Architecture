package estudo.projeto.port.in.command;

public record AlterarSuperiorCommand(
        Long atualSuperiorId,
        Long novoSuperiorId
) {
}
