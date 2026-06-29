package estudo.projeto.out.persistence;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;
import estudo.projeto.port.in.common.Pagina;
import estudo.projeto.port.in.query.ListUsuariosQuery;
import estudo.projeto.port.out.UsuarioPersistencePort;
import estudo.projeto.port.out.SubordinadosPort;
import estudo.projeto.port.out.UsuarioDeletePort;
import estudo.projeto.port.out.UsuarioFindPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UsuarioAdapter implements UsuarioPersistencePort, UsuarioFindPort, UsuarioDeletePort, SubordinadosPort {

    private final SpringUsuarioRepository repository;

    public UsuarioAdapter(SpringUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioJpaEntity entity = UsuarioJpaEntity.fromDomain(usuario);
        UsuarioJpaEntity entitySalva = repository.save(entity);
        return entitySalva.toDomain();
    }

    @Override
    public boolean hasSubordinados(Long superiorId) {
        return repository.existsBySuperiorId(superiorId);
    }

    @Override
    public void atribuirNovoSuperior(Long atualSuperiorId, Long novoSuperiorId) {
        UsuarioJpaEntity novoSuperior = null;

        if (novoSuperiorId != null) {
            novoSuperior = new UsuarioJpaEntity();
            novoSuperior.setId(novoSuperiorId);
        }

        repository.transferirSubordinados(atualSuperiorId, novoSuperior);
    }

    @Override
    public boolean existeSubordinadoComCargoMaiorOuIgual(Long superiorId, Cargo cargo) {
        List<Cargo> cargosInvalidos = Arrays.stream(Cargo.values())
                .filter(c -> c.getNivel() >= cargo.getNivel())
                .toList();

        return repository.existsBySuperiorIdAndCargoIn(superiorId, cargosInvalidos);
    }

    @Override
    public void deletar(Usuario usuario) {
        UsuarioJpaEntity usuarioJpaEntity = UsuarioJpaEntity.fromDomain(usuario);
        repository.delete(usuarioJpaEntity);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id).map(UsuarioJpaEntity::toDomain);
    }

    @Override
    public Pagina<Usuario> findAll(ListUsuariosQuery filtro, int pagina, int tamanho, String sortBy, String sortDirection) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && !sortBy.isBlank()){
            Sort.Direction direction = "desc".equals(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
            sort = Sort.by(direction, sortBy);
        }

        PageRequest pageRequest = PageRequest.of(pagina, tamanho, sort);

        Specification<UsuarioJpaEntity> specification = getUsuarioJpaEntitySpecification(filtro);

        Page<UsuarioJpaEntity> pageResult = repository.findAll(specification, pageRequest);

        List<Usuario> conteudo = pageResult.getContent().stream()
                .map(UsuarioJpaEntity::toDomain)
                .toList();

        return new Pagina<>(
                conteudo,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }

    @Override
    public boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id) {
        return repository.existsByEmailIgnoreCaseAndIdNot(email, id);
    }

    @Override
    public boolean existsByEmailIgnoreCase(String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

    private static Specification<UsuarioJpaEntity> getUsuarioJpaEntitySpecification(ListUsuariosQuery filtro) {
        Specification<UsuarioJpaEntity> specification = (root, query, cb) -> cb.conjunction();

        if (filtro.nome() != null && !filtro.nome().isBlank()){
            specification = specification.and((root, query, cb) -> cb.like(cb.lower(root.get("nome")), "%" + filtro.nome().toLowerCase() + "%"));
        }

        if (filtro.nome() != null && filtro.nome().isBlank()){
            specification = specification.and((root, query, cb) -> cb.equal(root.get("email"), filtro.email()));
        }

        if (filtro.cargo() != null){
            specification = specification.and((root, query, cb) -> cb.equal(root.get("cargo"), filtro.cargo()));
        }
        return specification;
    }
}
