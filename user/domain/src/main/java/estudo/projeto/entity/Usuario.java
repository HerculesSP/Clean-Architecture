package estudo.projeto.entity;


import estudo.projeto.exception.CargoObrigatorioException;
import estudo.projeto.exception.EmailInvalidoException;
import estudo.projeto.exception.NomeObrigatorioException;

public class Usuario {
    private final Long id;
    private final String nome;
    private final String email;
    private final Cargo cargo;
    private final Long superiorId;

    public Usuario(Long id, String nome, String email, Cargo cargo, Long superiorId) {
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
        if(nome.isBlank()){
            throw new NomeObrigatorioException();
        }
        if (cargo == null){
            throw new CargoObrigatorioException();
        }
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.superiorId = superiorId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Long getSuperiorId() {
        return superiorId;
    }
}

