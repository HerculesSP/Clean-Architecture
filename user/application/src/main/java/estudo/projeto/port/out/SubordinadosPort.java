package estudo.projeto.port.out;

public interface SubordinadosPort {
    boolean hasSubordinados(Long superiorId);
    void atribuirNovoSuperior(Long atualSuperiorId, Long novoSuperiorId);
}
