package com.ListaTarefas.Tarefas.repository;

import com.ListaTarefas.Tarefas.entities.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {
}
