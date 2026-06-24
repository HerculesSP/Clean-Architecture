package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.port.in.command.CreateUsuarioCommand;
import estudo.projeto.port.in.specification.UsuarioSpecification;
import estudo.projeto.port.out.SaveUsuarioPort;
import estudo.projeto.port.out.UsuarioFindPort;

import java.util.List;

public class CreateUsuarioUseCase {
    private final UsuarioFindPort usuarioFindPort;
    private final SaveUsuarioPort saveUsuarioPort;
    private final List<UsuarioSpecification> validations;

    public CreateUsuarioUseCase(UsuarioFindPort usuarioFindPort, SaveUsuarioPort saveUsuarioPort, List<UsuarioSpecification> validations) {
        this.usuarioFindPort = usuarioFindPort;
        this.saveUsuarioPort = saveUsuarioPort;
        this.validations = validations;
    }

    public Usuario executar(CreateUsuarioCommand command){

        Usuario superior = null;
        if (command.superiorId() != null) {
            superior = usuarioFindPort.findById(command.superiorId())
                    .orElseThrow(() -> new NotFoundException("Superior com o Id " + command.superiorId() + " não encontrado."));
        }

        Usuario usuario = new Usuario(null, command.nome(), command.email(), command.cargo(), superior);

        validations.forEach(usuarioSpecification -> usuarioSpecification.validar(usuario));

        return saveUsuarioPort.salvar(usuario);
    }
}
