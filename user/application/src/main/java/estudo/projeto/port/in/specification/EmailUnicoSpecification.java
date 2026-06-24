package estudo.projeto.port.in.specification;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.EmailExistenteException;
import estudo.projeto.port.out.UsuarioFindPort;

public class EmailUnicoSpecification implements UsuarioSpecification{

    private final UsuarioFindPort usuarioFindPort;

    public EmailUnicoSpecification(UsuarioFindPort usuarioFindPort) {
        this.usuarioFindPort = usuarioFindPort;
    }

    @Override
    public void validar(Usuario usuario) {
        if (usuario.getId() == null){
            if (usuarioFindPort.existsByEmailIgnoreCase(usuario.getEmail())){
                throw new EmailExistenteException();
            }
        } else {
            if (usuarioFindPort.existsByEmailIgnoreCaseAndIdNot(usuario.getEmail(), usuario.getId())){
                throw new EmailExistenteException();
            }
        }
    }
}
