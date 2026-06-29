package estudo.projeto.out.persistence;

import estudo.projeto.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpringUsuarioRepository extends
        JpaRepository<UsuarioJpaEntity, Long>,
        JpaSpecificationExecutor<UsuarioJpaEntity> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    boolean existsBySuperiorId(Long superiorId);

    boolean existsBySuperiorIdAndCargoIn(Long superiorId, List<Cargo> cargos);

    @Modifying
    @Transactional
    @Query("UPDATE UsuarioJpaEntity u SET u.superior = :novoSuperior WHERE u.superior.id = :atualSuperiorId")
    void transferirSubordinados(
            @Param("atualSuperiorId") Long atualSuperiorId,
            @Param("novoSuperior") UsuarioJpaEntity novoSuperior
    );
}
