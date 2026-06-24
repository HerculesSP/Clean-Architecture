package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.port.out.UsuarioFindPort;

public class GetUsuarioUseCase {

    private final UsuarioFindPort usuarioFindPort;

    public GetUsuarioUseCase(UsuarioFindPort usuarioFindPort) {
        this.usuarioFindPort = usuarioFindPort;
    }

    public Usuario executar(Long id){
        return usuarioFindPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID: " + id + " não encontrado."));
    }
}
