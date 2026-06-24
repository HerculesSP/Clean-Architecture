package estudo.projeto.port.out;

import estudo.projeto.entity.Usuario;
import estudo.projeto.port.in.common.Page;
import estudo.projeto.port.in.query.ListUsuariosQuery;

import java.util.List;
import java.util.Optional;

public interface UsuarioFindPort {
    Optional<Usuario> findById(Long id);
    Page<Usuario> findAll(ListUsuariosQuery filtro, int pagina, int tamanho, String sortBy, String sortDirection);
    boolean existsById(Long id);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    boolean existsByEmailIgnoreCase(String email);
}
