package estudo.projeto.port.out;

import estudo.projeto.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioFindPort {
    Optional<Usuario> findById(Long id);
    List<Usuario> findAll();
    boolean existsById(Long id);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
    boolean existsByEmailIgnoreCase(String email);
}
