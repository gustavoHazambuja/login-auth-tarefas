package login_auth_tasks.domain.dtos;

import login_auth_tasks.domain.entities.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    
    private String nome;
    private boolean concluida;



    public static TarefaDTO fromModel(Tarefa tarefa){
        return new TarefaDTO(
            tarefa.getNome(),
            tarefa.isConcluida()
        );
    }
}
