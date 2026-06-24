package estudo.projeto.port.in.command;

import estudo.projeto.entity.Cargo;

public record UpdateUsuarioCommand(
        Long id,
        String nome,
        String email,
        Cargo cargo,
        Long superiorId
) {
}
