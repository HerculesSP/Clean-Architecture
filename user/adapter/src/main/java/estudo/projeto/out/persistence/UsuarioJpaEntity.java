package estudo.projeto.out.persistence;


import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Email;
import estudo.projeto.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superior_id")
    private UsuarioJpaEntity superior;

    public Usuario toDomain(){
        Usuario superiorDomain = this.superior != null ? this.superior.toDomain() : null;
        return new Usuario(this.id, this.nome, new Email(this.email), this.cargo, superiorDomain);
    }

    public static UsuarioJpaEntity fromDomain(Usuario usuario){
        if (usuario == null) return null;

        UsuarioJpaEntity entity = new UsuarioJpaEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail().valor());
        entity.setCargo(usuario.getCargo());
        entity.setSuperior(fromDomain(usuario.getSuperior()));

        return entity;
    }
}
