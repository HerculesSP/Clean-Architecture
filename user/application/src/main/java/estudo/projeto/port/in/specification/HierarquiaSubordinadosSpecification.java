package estudo.projeto.port.in.specification;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.exception.ViolacaoDeHierarquiaException;
import estudo.projeto.port.out.SubordinadosPort;
import estudo.projeto.port.out.UsuarioFindPort;

public class HierarquiaSubordinadosSpecification implements UsuarioSpecification{
    private final UsuarioFindPort usuarioFindPort;
    private final SubordinadosPort subordinadosPort;

    public HierarquiaSubordinadosSpecification(UsuarioFindPort usuarioFindPort, SubordinadosPort subordinadosPort) {
        this.usuarioFindPort = usuarioFindPort;
        this.subordinadosPort = subordinadosPort;
    }

    @Override
    public void validar(Usuario usuario) {
        if (usuario.getId() == null){
            return;
        }

        Usuario usuarioAntigo = usuarioFindPort.findById(usuario.getId())
                .orElseThrow(() -> new NotFoundException("Usuario com o Id " + usuario.getId() + " não encontrado."));

        if (usuario.getCargo().equals(usuarioAntigo.getCargo())){
            return;
        }

        boolean hasViolacao = subordinadosPort.existeSubordinadoComCargoMaiorOuIgual(usuario.getId(), usuario.getCargo());

        if (hasViolacao){
            throw new ViolacaoDeHierarquiaException(
                    String.format("Não é possível alterar o cargo para %s pois este usuário possui subordinados " +
                                    "com cargo igual ou superior ao novo cargo.",
                            usuario.getCargo())
            );
        }

    }
}
