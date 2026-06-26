package estudo.projeto.port.in.common;

import java.util.List;

public record Pagina<T>(
        List<T> conteudo,
        int paginaAtual,
        int tamanhoPagina,
        long totalElementos,
        int totalPaginas
) {
}
