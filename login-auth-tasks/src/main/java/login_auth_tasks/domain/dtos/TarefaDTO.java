package login_auth_tasks.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import login_auth_tasks.domain.entities.Tarefa;
import login_auth_tasks.domain.enums.EstadoTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O estado da tarefa é obrigatório")
    private EstadoTarefa status;



    public static TarefaDTO fromModel(Tarefa tarefa){
        return new TarefaDTO(
            tarefa.getId(),
            tarefa.getNome(),
            tarefa.getStatus()
        );
    }
}
