package estudo.projeto.port.in.strategy;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.SuperiorObrigatorioException;
import estudo.projeto.port.out.SubordinadosPort;

public class DeleteGerenteStrategy implements DeleteUsuarioStrategy{
    private final SubordinadosPort subordinadosPort;

    public DeleteGerenteStrategy(SubordinadosPort subordinadosPort) {
        this.subordinadosPort = subordinadosPort;
    }

    @Override
    public boolean seAplicaA(Cargo cargo) {
        return cargo == Cargo.GERENTE;
    }

    @Override
    public void resolver(Usuario usuario) {
        if (subordinadosPort.hasSubordinados(usuario.getId())){
            throw new SuperiorObrigatorioException(
                    "O gerente possui subordinados e não pode ser deltado. Altere o superior dos subordinados antes."
            );
        }
    }
}
