package com.ListaTarefas.Tarefas.controller;

import com.ListaTarefas.Tarefas.entities.Tarefas;
import com.ListaTarefas.Tarefas.services.TarefasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {
    @Autowired
    private TarefasService tarefasService;

    public TarefasController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> buscarTarefaId(@PathVariable Long id){
        Tarefas tarefas = tarefasService.buscarTarefasId(id);
        if (tarefas != null){
            return ResponseEntity.ok(tarefas);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<Tarefas>> buscarTodasTarefas(){
        List<Tarefas> Tarefas = tarefasService.buscarTodasTarefas();
        return ResponseEntity.ok(Tarefas);
    }

    @PostMapping("/")
    public ResponseEntity<Tarefas> salvaTarefa(@RequestBody @Valid Tarefas tarefas){
        Tarefas saveTarefas = tarefasService.salvarTarefas(tarefas);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTarefas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> alteraTarefas(@PathVariable Long id, @RequestBody @Valid Tarefas tarefas){
        Tarefas atualizaTarefas = tarefasService.atualizarTarefas(id, tarefas);
        if (atualizaTarefas != null){
            return ResponseEntity.ok(tarefas);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefas> apagaTarefa(@PathVariable Long id){
        boolean apagaTarefas = tarefasService.apagarTarefas(id);
        if (apagaTarefas){
           return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
