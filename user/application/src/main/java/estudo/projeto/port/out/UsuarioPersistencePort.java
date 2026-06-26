package estudo.projeto.port.out;

import estudo.projeto.entity.Usuario;

public interface UsuarioPersistencePort {
    Usuario salvar(Usuario usuario);
}
