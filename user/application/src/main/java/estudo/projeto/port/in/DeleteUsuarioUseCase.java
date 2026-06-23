package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.exception.StrategyInexistenteException;
import estudo.projeto.port.in.strategy.DeleteUsuarioStrategy;
import estudo.projeto.port.out.UsuarioDeletePort;
import estudo.projeto.port.out.UsuarioFindPort;

import java.util.List;

public class DeleteUsuarioUseCase {
    private final UsuarioDeletePort usuarioDeletePort;
    private final UsuarioFindPort usuarioFindPort;
    private final List<DeleteUsuarioStrategy> strategies;

    public DeleteUsuarioUseCase(UsuarioDeletePort usuarioDeletePort, UsuarioFindPort usuarioFindPort, List<DeleteUsuarioStrategy> strategies) {
        this.usuarioDeletePort = usuarioDeletePort;
        this.usuarioFindPort = usuarioFindPort;
        this.strategies = strategies;
    }

    public void executar(Long id, Long novoSuperiorId){
        Usuario usuario = usuarioFindPort.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário com ID: " + id + " não encontrado."));

        this.strategies.stream()
                .filter(strategy -> strategy.seAplicaA(usuario.getCargo()))
                .findFirst()
                .orElseThrow(StrategyInexistenteException::new);

        usuarioDeletePort.deletar(usuario.getId());
    }
}
