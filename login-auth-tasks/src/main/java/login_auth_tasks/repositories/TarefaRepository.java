package login_auth_tasks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import login_auth_tasks.domain.entities.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
