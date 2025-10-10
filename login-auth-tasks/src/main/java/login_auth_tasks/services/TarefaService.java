package login_auth_tasks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import login_auth_tasks.domain.dtos.TarefaDTO;
import login_auth_tasks.domain.entities.Tarefa;
import login_auth_tasks.exceptions.TarefaNotFoundExcepton;
import login_auth_tasks.repositories.TarefaRepository;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;


    public boolean validaTarefa(Long id){
        return tarefaRepository.existsById(id);
    }

    public boolean criarTarefa(TarefaDTO dto){
        Tarefa tarefa = toModel(dto);
        tarefaRepository.save(tarefa);

        return true;
    }

    public Page<TarefaDTO> listarTarefas(Pageable pageable){
        return tarefaRepository.findAll(pageable)
            .map(TarefaDTO::fromModel);
    }

    public boolean deletarTarefa(Long id){
        if(!tarefaRepository.existsById(id)){
            return false;
        }
        tarefaRepository.deleteById(id);
        return true;
    }

    public TarefaDTO atualizarTarefa(Long id, TarefaDTO dto){
        Tarefa tarefaEncontrada = tarefaRepository.findById(id)
            .orElseThrow(() -> new TarefaNotFoundExcepton("Tarefa com ID " + id + " não encontrada."));

        tarefaEncontrada.setNome(dto.getNome());
        tarefaEncontrada.setStatus(dto.getStatus());

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaEncontrada);
        return TarefaDTO.fromModel(tarefaAtualizada);
    }


    private Tarefa toModel(TarefaDTO dto){
        return new Tarefa(
            dto.getNome(),
            dto.getStatus()
        );
    }
}
