package login_auth_tasks.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import login_auth_tasks.exceptions.UserNotFoundException;
import login_auth_tasks.repositories.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository){
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
      
        var token = this.recuperarToken(request);

        if(token != null){
            var login = tokenService.validarToken(token);

            UserDetails usuario = usuarioRepository.findByEmail(login).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado. "));
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
      


    }


    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;

        return authHeader.replace("Bearer", "").trim();
    }



}
