package estudo.projeto.port.in.query;

import estudo.projeto.entity.Cargo;

public record ListUsuariosQuery(
        String nome,
        String email,
        Cargo cargo
) {}

