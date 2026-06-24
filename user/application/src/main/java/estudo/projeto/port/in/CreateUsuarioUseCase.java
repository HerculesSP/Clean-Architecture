package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.port.in.Command.CreateUserCommand;
import estudo.projeto.port.in.Specification.UsuarioSpecification;
import estudo.projeto.port.out.SalvarUsuarioPort;
import estudo.projeto.port.out.UsuarioFindPort;

import java.util.List;

public class CreateUsuarioUseCase {
    private final UsuarioFindPort usuarioFindPort;
    private final SalvarUsuarioPort salvarUsuarioPort;
    private final List<UsuarioSpecification> validations;

    public CreateUsuarioUseCase(UsuarioFindPort usuarioFindPort, SalvarUsuarioPort salvarUsuarioPort, List<UsuarioSpecification> validations) {
        this.usuarioFindPort = usuarioFindPort;
        this.salvarUsuarioPort = salvarUsuarioPort;
        this.validations = validations;
    }

    public Usuario executar(CreateUserCommand command){

        Usuario superior = null;

        if (command.superiorId() != null) {
            superior = usuarioFindPort.findById(command.superiorId())
                    .orElseThrow(() -> new NotFoundException("Superior com o Id " + command.superiorId() + " não encontrado."));
        }

        Usuario usuario = new Usuario(null, command.nome(), command.email(), command.cargo(), superior);

        validations.forEach(usuarioSpecification -> usuarioSpecification.validar(usuario));

        return salvarUsuarioPort.salvar(usuario);
    }
}
