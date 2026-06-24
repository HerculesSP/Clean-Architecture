package estudo.projeto.port.in.Specification;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;
import estudo.projeto.exception.ViolacaoDeHierarquiaException;

public class HierarquiaValidaSpecification implements UsuarioSpecification{
    @Override
    public void validar(Usuario usuario) {
        if (usuario.getSuperior() == null){
            if (usuario.getCargo() != Cargo.DIRETOR){
                throw new ViolacaoDeHierarquiaException(
                        "O usuário com cargo de " + usuario.getCargo() + " precisa ter um superior."
                );
            }
        } else {
            Cargo cargoSuperior  = usuario.getSuperior().getCargo();
            if (!cargoSuperior.podeSerSuperiorDe(usuario.getCargo())){
                throw new ViolacaoDeHierarquiaException(
                        "Um " + cargoSuperior + " não pode ser superior de um " + usuario.getCargo() + "."
                );
            }
        }
    }
}
