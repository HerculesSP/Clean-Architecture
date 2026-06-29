package estudo.projeto.port.in;

import estudo.projeto.entity.Email;
import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.port.in.command.UpdateUsuarioCommand;
import estudo.projeto.port.in.specification.UsuarioSpecification;
import estudo.projeto.port.out.UsuarioPersistencePort;
import estudo.projeto.port.out.UsuarioFindPort;

import java.util.List;

public class UpdateUsuarioUseCase {

    private final UsuarioFindPort usuarioFindPort;
    private final UsuarioPersistencePort usuarioPersistencePort;
    private final List<UsuarioSpecification> validations;

    public UpdateUsuarioUseCase(UsuarioFindPort usuarioFindPort, UsuarioPersistencePort usuarioPersistencePort, List<UsuarioSpecification> validations) {
        this.usuarioFindPort = usuarioFindPort;
        this.usuarioPersistencePort = usuarioPersistencePort;
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

        usuario.atualizarDados(command.nome(), new Email(command.email()), command.cargo(), superior);

        validations.forEach(usuarioSpecification -> usuarioSpecification.validar(usuario));

        return usuarioPersistencePort.salvar(usuario);

    }
}
