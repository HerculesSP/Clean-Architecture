package estudo.projeto.port.in;

import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.NotFoundException;
import estudo.projeto.exception.ViolacaoDeHierarquiaException;
import estudo.projeto.port.in.command.AlterarSuperiorCommand;
import estudo.projeto.port.out.SubordinadosPort;
import estudo.projeto.port.out.UsuarioFindPort;

public class AlterarSuperiorUseCase {

    private final UsuarioFindPort usuarioFindPort;
    private final SubordinadosPort subordinadosPort;

    public AlterarSuperiorUseCase(UsuarioFindPort usuarioFindPort, SubordinadosPort subordinadosPort) {
        this.usuarioFindPort = usuarioFindPort;
        this.subordinadosPort = subordinadosPort;
    }

    public void executar(AlterarSuperiorCommand command){
        if (command.atualSuperiorId().equals(command.novoSuperiorId())) {
            throw new ViolacaoDeHierarquiaException("O superior de destino não pode ser o mesmo de origem.");
        }

        Usuario atualSuperior = usuarioFindPort.findById(command.atualSuperiorId())
                .orElseThrow(() -> new NotFoundException("Usuário com ID: " + command.novoSuperiorId() + " não encontrado."));

        Usuario novoSuperior = usuarioFindPort.findById(command.novoSuperiorId())
                .orElseThrow(() -> new NotFoundException("Usuário com ID: " + command.novoSuperiorId() + " não encontrado."));

        if (novoSuperior.getSuperior() != null && novoSuperior.getSuperior().getId().equals(atualSuperior.getId())) {
            throw new ViolacaoDeHierarquiaException("O novo superior não pode ser um subordinado do superior atual.");
        }

        boolean hasViolacao = subordinadosPort.existeSubordinadoComCargoMaiorOuIgual(
                atualSuperior.getId(),
                novoSuperior.getCargo()
        );

        if (hasViolacao) {
            throw new ViolacaoDeHierarquiaException(
                    String.format("Não é possível transferir a equipe para o usuário do cargo %s, pois a equipe de origem " +
                                    "possui subordinados com cargo igual ou superior ao dele.",
                            novoSuperior.getCargo())
            );
        }

        subordinadosPort.atribuirNovoSuperior(atualSuperior.getId(), command.novoSuperiorId());
    }
}
