package com.labdessoft.roteiro01.service;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        if (task.getTaskType() == null) {
            task.setTaskType(TaskType.DATA); // Define o tipo de tarefa como "Data"
        }
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Métodos abaixo precisam ser implementados com as lógicas específicas
    public List<Task> gerenciarTarefas() {
        // Implementar a lógica para gerenciar tarefas
        return taskRepository.findAll();
    }

    public List<Task> concluirTarefas() {
        // Implementar a lógica para concluir tarefas
        return taskRepository.findByCompleted(true);
    }

    public List<Task> priorizarTarefas() {
        // Implementar a lógica para priorizar tarefas
        return taskRepository.findByPriority("high");
    }

    public List<Task> categorizarTarefas() {
        // Implementar a lógica para categorizar tarefas
        return taskRepository.findByCategory("work");
    }
}
