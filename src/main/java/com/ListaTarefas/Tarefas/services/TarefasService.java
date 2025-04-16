package com.ListaTarefas.Tarefas.services;

import com.ListaTarefas.Tarefas.entities.Tarefas;
import com.ListaTarefas.Tarefas.repository.TarefasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefasService {
    @Autowired

    private final TarefasRepository tarefasRepository;

    public TarefasService(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    public List<Tarefas> buscarTodasTarefas(){
        return tarefasRepository.findAll();
    }
    public Tarefas buscarTarefasId(Long id){
        Optional <Tarefas> tarefa = tarefasRepository.findById(id);
        return tarefa.orElse(null);
    }

    public Tarefas salvarTarefas(Tarefas Tarefas){
        return tarefasRepository.save(Tarefas);
    }

    public Tarefas atualizarTarefas(Long id, Tarefas atTarefas){
        Optional <Tarefas> existeTarefa = tarefasRepository.findById(id);

        if(existeTarefa.isPresent()){
            atTarefas.setId(id);
            return tarefasRepository.save(atTarefas);
        }
        else{
            return null;
        }
    }

    public boolean apagarTarefas(Long id){
        Optional <Tarefas> existeTarefa = tarefasRepository.findById(id);

        if (existeTarefa.isPresent()){
            tarefasRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
