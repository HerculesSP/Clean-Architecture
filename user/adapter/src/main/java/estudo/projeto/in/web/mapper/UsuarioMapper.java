package estudo.projeto.in.web.mapper;

import estudo.projeto.entity.Usuario;
import estudo.projeto.in.web.request.TransferirSubordinadosRequest;
import estudo.projeto.in.web.request.UsuarioRequest;
import estudo.projeto.in.web.response.PagedResponse;
import estudo.projeto.in.web.response.UsuarioResponse;
import estudo.projeto.port.in.command.AlterarSuperiorCommand;
import estudo.projeto.port.in.command.CreateUsuarioCommand;
import estudo.projeto.port.in.command.UpdateUsuarioCommand;
import estudo.projeto.port.in.common.Pagina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    CreateUsuarioCommand toCreateCommand(UsuarioRequest request);

    @Mapping(target = "id", source = "id")
    UpdateUsuarioCommand toUpdateCommand(Long id, UsuarioRequest request);

    AlterarSuperiorCommand toAlterarSuperiorCommand(TransferirSubordinadosRequest request);

    UsuarioResponse toResponse(Usuario domain);
    List<UsuarioResponse> toResponseList(List<Usuario> domainList);

    default PagedResponse<UsuarioResponse> toPagedResponse(Pagina<Usuario> paginaDomain) {
        if (paginaDomain == null) {
            return null;
        }

        List<UsuarioResponse> content = toResponseList(paginaDomain.conteudo());

        return new PagedResponse<>(
                content,
                paginaDomain.paginaAtual(),
                paginaDomain.tamanhoPagina(),
                paginaDomain.totalElementos(),
                paginaDomain.totalPaginas()
        );
    }
}
