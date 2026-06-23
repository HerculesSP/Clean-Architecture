package estudo.projeto.port;

public interface SubordinadosPort {
    boolean hasSubordinados(Long superiorId);
    void atribuirNovoSuperior(Long atualSuperiorId, Long novoSuperiorId);
}
