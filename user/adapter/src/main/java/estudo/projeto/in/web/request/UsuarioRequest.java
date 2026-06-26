package estudo.projeto.in.web.request;

import estudo.projeto.entity.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsuarioRequest (
        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotNull
        Cargo cargo,

        @Positive
        Long superiorId
) {
}
