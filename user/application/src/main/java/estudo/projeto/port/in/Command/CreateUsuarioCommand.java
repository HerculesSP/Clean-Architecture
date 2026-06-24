package estudo.projeto.port.in.Command;

import estudo.projeto.entity.Cargo;

public record CreateUsuarioCommand(
        String nome,
        String email,
        Cargo cargo,
        Long superiorId
) {
}
