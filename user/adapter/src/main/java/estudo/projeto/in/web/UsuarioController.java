package estudo.projeto.in.web;

import estudo.projeto.entity.Cargo;
import estudo.projeto.entity.Usuario;
import estudo.projeto.in.web.mapper.UsuarioMapper;
import estudo.projeto.in.web.request.TransferirSubordinadosRequest;
import estudo.projeto.in.web.request.UsuarioRequest;
import estudo.projeto.in.web.response.PagedResponse;
import estudo.projeto.in.web.response.UsuarioResponse;
import estudo.projeto.port.in.*;
import estudo.projeto.port.in.command.AlterarSuperiorCommand;
import estudo.projeto.port.in.command.CreateUsuarioCommand;
import estudo.projeto.port.in.command.UpdateUsuarioCommand;
import estudo.projeto.port.in.common.Pagina;
import estudo.projeto.port.in.query.ListUsuariosQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioMapper usuarioMapper;
    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final GetUsuarioUseCase getUsuarioUseCase;
    private final ListUsuarioUseCase listUsuarioUseCase;
    private final AlterarSuperiorUseCase alterarSuperiorUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;

    public UsuarioController(UsuarioMapper usuarioMapper, CreateUsuarioUseCase createUsuarioUseCase,
                             UpdateUsuarioUseCase updateUsuarioUseCase, GetUsuarioUseCase getUsuarioUseCase,
                             ListUsuarioUseCase listUsuarioUseCase, AlterarSuperiorUseCase alterarSuperiorUseCase,
                             DeleteUsuarioUseCase deleteUsuarioUseCase) {
        this.usuarioMapper = usuarioMapper;
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.getUsuarioUseCase = getUsuarioUseCase;
        this.listUsuarioUseCase = listUsuarioUseCase;
        this.alterarSuperiorUseCase = alterarSuperiorUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar (@Valid @RequestBody UsuarioRequest request){
        CreateUsuarioCommand command = usuarioMapper.toCreateCommand(request);
        Usuario usuario = createUsuarioUseCase.executar(command);
        return ResponseEntity.status(201).body(usuarioMapper.toResponse(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar (
            @PathVariable @Positive Long id,
            @Valid @RequestBody UsuarioRequest request
    ){
        UpdateUsuarioCommand command = usuarioMapper.toUpdateCommand(id, request);
        Usuario usuario = updateUsuarioUseCase.executar(command);
        return ResponseEntity.ok(usuarioMapper.toResponse(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable @Positive Long id){
        deleteUsuarioUseCase.executar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscar (@PathVariable @Positive Long id){
        Usuario usuario = getUsuarioUseCase.executar(id);
        return ResponseEntity.ok(usuarioMapper.toResponse(usuario));
    }

    @PatchMapping("/{atualSuperiorId}/transferir-subordinados")
    public ResponseEntity<Void> trocar (
            @Valid @RequestBody TransferirSubordinadosRequest request,
            @PathVariable @Positive Long atualSuperiorId){
        AlterarSuperiorCommand command = usuarioMapper.toAlterarSuperiorCommand(atualSuperiorId, request);
        alterarSuperiorUseCase.executar(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UsuarioResponse>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Cargo cargo,
            @RequestParam(defaultValue = "0") @PositiveOrZero int pagina,
            @RequestParam(defaultValue = "10") @PositiveOrZero int tamanho,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
            ){

        ListUsuariosQuery filtro = new ListUsuariosQuery(nome,email, cargo);

        Pagina<Usuario> paginaDomain = listUsuarioUseCase.executar(filtro, pagina, tamanho, sortBy, sortDirection);
        return ResponseEntity.ok(usuarioMapper.toPagedResponse(paginaDomain));
    }
}
