package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.port.in.command.UpdateUsuarioCommand;
import estudo.projeto.port.in.specification.UsuarioSpecification;
import estudo.projeto.port.out.SaveUsuarioPort;
import estudo.projeto.port.out.UsuarioFindPort;

import java.util.List;

public class UpdateUsuarioUseCase {

    private final UsuarioFindPort usuarioFindPort;
    private final SaveUsuarioPort saveUsuarioPort;
    private final List<UsuarioSpecification> validations;

    public UpdateUsuarioUseCase(UsuarioFindPort usuarioFindPort, SaveUsuarioPort saveUsuarioPort, List<UsuarioSpecification> validations) {
        this.usuarioFindPort = usuarioFindPort;
        this.saveUsuarioPort = saveUsuarioPort;
        this.validations = validations;
    }


    public Usuario executar(UpdateUsuarioCommand command){
        Usuario usuario = usuarioFindPort.findById(command.id())
                .orElseThrow(() -> new NotFoundException("Usuário com ID: " + command.id() + " não encontrado."));

        Usuario superior = null;
        if (command.superiorId() != null) {
            superior = usuarioFindPort.findById(command.superiorId())
                    .orElseThrow(() -> new NotFoundException("Superior com o Id " + command.superiorId() + " não encontrado."));
        }

        usuario.atualizarDados(command.nome(), command.email(), command.cargo(), superior);

        validations.forEach(usuarioSpecification -> usuarioSpecification.validar(usuario));

        return saveUsuarioPort.salvar(usuario);

    }
}
