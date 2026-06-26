package estudo.projeto.in.web.request;

import jakarta.validation.constraints.Positive;

public record TransferirSubordinadosRequest (
        @Positive Long atualSuperiorId,
        @Positive Long novoSuperiorId
) {
}
