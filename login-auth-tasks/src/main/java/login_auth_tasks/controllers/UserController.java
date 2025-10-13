package login_auth_tasks.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import login_auth_tasks.domain.dtos.TokenDTO;
import login_auth_tasks.domain.dtos.UsuarioLoginDTO;
import login_auth_tasks.domain.dtos.UsuarioRegisterDTO;
import login_auth_tasks.domain.entities.Usuario;
import login_auth_tasks.exceptions.UserNotFoundException;
import login_auth_tasks.infra.security.TokenService;
import login_auth_tasks.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioLoginDTO body){

        Usuario usuario = usuarioRepository.findByEmail(body.getEmail()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        if(passwordEncoder.matches(body.getSenha(), usuario.getPassword())){
            String token = tokenService.gerarToken(usuario);
            return ResponseEntity.ok(new TokenDTO(token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioRegisterDTO body){

        Optional<Usuario> user = usuarioRepository.findByEmail(body.getEmail());

        if(user.isEmpty()){
            Usuario newUser = new Usuario();
            newUser.setSenha(passwordEncoder.encode(body.getSenha()));
            newUser.setEmail(body.getEmail());
            newUser.setRole(body.getRole());
            this.usuarioRepository.save(newUser);


            String token = tokenService.gerarToken(newUser);
            return ResponseEntity.ok(new TokenDTO(token));
        }

        return ResponseEntity.badRequest().build();
    }
}
