package estudo.projeto.port;

import estudo.projeto.entity.Usuario;

import java.util.Optional;

public interface UsuarioFindPort {
    Optional<Usuario> findById(Long id);
}
