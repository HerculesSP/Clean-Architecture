package estudo.projeto.port.in.Command;

import estudo.projeto.entity.Cargo;

public record CreateUserCommand(
        String nome,
        String email,
        Cargo cargo,
        Long superiorId
) {
}
