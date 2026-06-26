package estudo.projeto.in.web.response;

import estudo.projeto.entity.Cargo;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        Cargo cargo,
        UsuarioResponse superior
) {

}
