package com.labdessoft.roteiro01.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/task")
    @Operation(summary = "Lista todas as tarefas da lista")
    public String listAll() {
        return "Listar todas as tasks";
    }

    @GetMapping("/task/{id}")
    @Operation(summary = "Cria uma tarefa lista pelo ID")
    public String retrieveTaskById() {
        return "tarefa craiada";
    }

    @PostMapping("/task")
    @Operation(summary = "move a tarefa na lista de tarefas")
    public String addTask() {
        return "tarefa movida";
    }

    @GetMapping("/task/{id}/concluir")
    @Operation(summary = "conclui a tarefa pelo ID")
    public String describeTaskById() {
        return "tarefa concluida";
    }
}
