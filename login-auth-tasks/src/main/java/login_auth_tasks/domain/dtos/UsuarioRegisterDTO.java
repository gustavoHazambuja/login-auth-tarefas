package login_auth_tasks.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import login_auth_tasks.domain.entities.Usuario;
import login_auth_tasks.domain.enums.tipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegisterDTO {

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email incorreto")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6)
    private String senha;

    @NotBlank
    private tipoUsuario role;




    public static UsuarioRegisterDTO fromModel(Usuario usuario){
        return new UsuarioRegisterDTO(
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getRole()
        );
    }
}
