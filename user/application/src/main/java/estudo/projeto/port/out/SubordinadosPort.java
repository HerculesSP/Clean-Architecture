package estudo.projeto.port.out;

import estudo.projeto.entity.Cargo;

public interface SubordinadosPort {
    boolean hasSubordinados(Long superiorId);
    void atribuirNovoSuperior(Long atualSuperiorId, Long novoSuperiorId);
    boolean existeSubordinadoComCargoMaiorOuIgual(Long supervisorId, Cargo cargo);
}
