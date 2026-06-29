package estudo.projeto.config;

import estudo.projeto.port.in.*;
import estudo.projeto.port.in.specification.UsuarioSpecification;
import estudo.projeto.port.in.strategy.DeleteUsuarioStrategy;
import estudo.projeto.port.out.SubordinadosPort;
import estudo.projeto.port.out.UsuarioDeletePort;
import estudo.projeto.port.out.UsuarioFindPort;
import estudo.projeto.port.out.UsuarioPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCase(UsuarioFindPort usuarioFindPort,
                                                     UsuarioPersistencePort usuarioPersistencePort,
                                                     List<UsuarioSpecification> validations) {

        return new CreateUsuarioUseCase(usuarioFindPort, usuarioPersistencePort, validations);
    }

    @Bean
    public AlterarSuperiorUseCase alterarSuperiorUseCase(UsuarioFindPort usuarioFindPort,
                                                         SubordinadosPort subordinadosPort){

        return new AlterarSuperiorUseCase(usuarioFindPort, subordinadosPort);

    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCase(UsuarioDeletePort usuarioDeletePort,
                                                     UsuarioFindPort usuarioFindPort,
                                                     List<DeleteUsuarioStrategy> strategies){

        return new DeleteUsuarioUseCase(usuarioDeletePort, usuarioFindPort, strategies);
    }

    @Bean
    public GetUsuarioUseCase getUsuarioUseCase(UsuarioFindPort usuarioFindPort){
        return new GetUsuarioUseCase(usuarioFindPort);
    }

    @Bean
    public ListUsuarioUseCase listUsuarioUseCase(UsuarioFindPort usuarioFindPort){
        return new ListUsuarioUseCase(usuarioFindPort);
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCase(UsuarioFindPort usuarioFindPort,
                                                     UsuarioPersistencePort usuarioPersistencePort,
                                                     List<UsuarioSpecification> specifications){
        return new UpdateUsuarioUseCase(usuarioFindPort, usuarioPersistencePort, specifications);
    }
}
