package login_auth_tasks.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import login_auth_tasks.domain.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
}
