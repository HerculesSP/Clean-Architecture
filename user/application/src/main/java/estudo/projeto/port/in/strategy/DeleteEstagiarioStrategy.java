package estudo.projeto.port.in.strategy;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;

public class DeleteEstagiarioStrategy implements DeleteUsuarioStrategy{
    @Override
    public boolean seAplicaA(Cargo cargo) {
        return cargo == Cargo.ESTAGIARIO;
    }

    @Override
    public void resolver(Usuario usuario, Long novoSupervisorId) {

    }
}
