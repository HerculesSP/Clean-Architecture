package estudo.projeto.port.in.strategy;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;

public interface DeleteUsuarioStrategy {
    boolean seAplicaA(Cargo cargo);
    void resolver(Usuario usuario);
}
