package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.port.in.common.Page;
import estudo.projeto.port.in.query.ListUsuariosQuery;
import estudo.projeto.port.out.UsuarioFindPort;

public class ListUsuarioUseCase {

    private final UsuarioFindPort usuarioFindPort;

    public ListUsuarioUseCase(UsuarioFindPort usuarioFindPort) {
        this.usuarioFindPort = usuarioFindPort;
    }

    public Page<Usuario> executar(ListUsuariosQuery filtro, int pagina, int tamanho, String sortBy, String sortDirection) {
        return usuarioFindPort.findAll(filtro, pagina, tamanho, sortBy, sortDirection);
    }}
