package estudo.projeto.port.in.common;

import java.util.List;

public record Page<T>(
        List<T> conteudo,
        int paginaAtual,
        int tamanhoPagina,
        long totalElementos,
        int totalPaginas
) {
}
