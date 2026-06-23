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
    public void resolver(Usuario usuario, Long novoSupervisorId) {
        if (subordinadosPort.hasSubordinados(usuario.getId())){
            if (novoSupervisorId == null){
                throw new SuperiorObrigatorioException(
                        "Para deletar um gerente com subordinados, é obrigatório informar o ID do novo supervisor."
                );
            }
            subordinadosPort.atribuirNovoSuperior(usuario.getId(), novoSupervisorId);
        }
    }
}
