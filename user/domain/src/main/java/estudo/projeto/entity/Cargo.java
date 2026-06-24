package estudo.projeto.entity;

public enum Cargo {
    DIRETOR(4),
    GERENTE(3),
    ANALISTA(2),
    ESTAGIARIO(1);

    private final int nivel;

    Cargo(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public boolean podeSerSuperiorDe(Cargo outroCargo) {
        return this.nivel > outroCargo.getNivel();
    }
}
