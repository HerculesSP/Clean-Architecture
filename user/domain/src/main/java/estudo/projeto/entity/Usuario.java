package estudo.projeto.entity;


import estudo.projeto.exception.CargoObrigatorioException;
import estudo.projeto.exception.EmailInvalidoException;
import estudo.projeto.exception.NomeObrigatorioException;
import estudo.projeto.exception.ViolacaoDeHierarquiaException;

public class Usuario {
    private final Long id;
    private String nome;
    private String email;
    private Cargo cargo;
    private Usuario superior;

    public Usuario(Long id, String nome, String email, Cargo cargo, Usuario superior) {
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
        this.superior = superior;
    }

    public void atualizarDados(String nome, String email, Cargo cargo, Usuario superior){
        if (superior != null && this.id != null && this.id.equals(superior.getId())) {
            throw new ViolacaoDeHierarquiaException("Um usuário não pode ser superior de si mesmo.");
        }

        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.superior = superior;

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

    public Usuario getSuperior() {
        return superior;
    }
}

