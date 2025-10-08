package login_auth_tasks.controllers;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import login_auth_tasks.domain.dtos.TarefaDTO;
import login_auth_tasks.services.TarefaService;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
    
    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }


    @GetMapping(value = "/validaTarefa/{id}")
    public boolean validaTarefa(@PathVariable Long id){
        return tarefaService.validaTarefa(id);
    }

    @PostMapping(value = "/criarTarefa")
    public ResponseEntity<?> criarTarefa(@RequestBody @Valid TarefaDTO dto){
        boolean resposta = tarefaService.criarTarefa(dto);

        if(resposta){
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("resposta: ", resposta, "mensagem: ", "Tarefa criada com sucesso."));
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("resposta: ", resposta, "mensagem: ", "Tarefa com id já existente."));
        }
    }


    @GetMapping
    public ResponseEntity<Page<TarefaDTO>> listarTarefas(Pageable pageable){
        Page<TarefaDTO> result = tarefaService.listarTarefas(pageable);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/deletarTarefa/{id}")
    public boolean deletarTarefa(@PathVariable Long id){
        return tarefaService.deletarTarefa(id);
    }

    @PutMapping(value = "/atualizarTarefa/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto){
        TarefaDTO result = tarefaService.atualizarTarefa(id, dto);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
