package estudo.projeto.port.in.strategy;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.SuperiorObrigatorioException;
import estudo.projeto.port.out.SubordinadosPort;

public class DeleteDiretorStrategy implements DeleteUsuarioStrategy{

    private final SubordinadosPort subordinadosPort;

    public DeleteDiretorStrategy(SubordinadosPort subordinadosPort) {
        this.subordinadosPort = subordinadosPort;
    }

    @Override
    public boolean seAplicaA(Cargo cargo) {
        return cargo == Cargo.DIRETOR;
    }

    @Override
    public void resolver(Usuario usuario) {
        if (subordinadosPort.hasSubordinados(usuario.getId())){
            throw new SuperiorObrigatorioException(
                    "Para deletar um gerente com subordinados, é obrigatório informar o ID do novo superior."
            );
        }
    }
}
