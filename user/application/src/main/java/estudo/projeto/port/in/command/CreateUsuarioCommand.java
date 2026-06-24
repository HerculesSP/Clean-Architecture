package estudo.projeto.port.in.command;

import estudo.projeto.entity.Cargo;

public record CreateUsuarioCommand(
        String nome,
        String email,
        Cargo cargo,
        Long superiorId
) {
}
