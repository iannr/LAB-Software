package com.labdessoft.roteiro01.service;

import com.example.roteiro01.entity.Task;
import com.example.roteiro01.entity.TaskStatus;
import com.example.roteiro01.entity.TaskType;
import com.example.roteiro01.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task criarTarefa(Task tarefa) {
        if (tarefa.getType() == TaskType.DATA && tarefa.getDueDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data da tarefas do tipo 'Data' tem que ser igual ou após a data atual.");
        }
        setTaskStatus(tarefa);
        return taskRepository.save(tarefa);
    }

    public Task atualizarTarefa(Long id, Task tarefaAtualizada) {
        Optional<Task> tarefaOptional = taskRepository.findById(id);
        if (tarefaOptional.isPresent()) {
            Task tarefaExistente = tarefaOptional.get();
            // Atualizar propriedades comuns
            tarefaExistente.setDescription(tarefaAtualizada.getDescription());
            tarefaExistente.setCompleted(tarefaAtualizada.getCompleted());
            // Atualizar propriedades específicas do tipo
            tarefaExistente.setType(tarefaAtualizada.getType());
            tarefaExistente.setDeadlineInDays(tarefaAtualizada.getDeadlineInDays());
            tarefaExistente.setDueDate(tarefaAtualizada.getDueDate());
            tarefaExistente.setPriority(tarefaAtualizada.getPriority());
            setTaskStatus(tarefaExistente); // Atualizar status
            return taskRepository.save(tarefaExistente);
        } else {
            return null;
        }
    }

    public void deletarTarefa(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> obterTodasTarefas() {
        List<Task> tarefas = taskRepository.findAll();
        for (Task task : tarefas) {
            setTaskStatus(task);
        }
        return tarefas;
    }

    private void setTaskStatus(Task task) {
        LocalDate currentDate = LocalDate.now();
        if (task.getType() == TaskType.DATA) {
            if (task.getDueDate().isBefore(currentDate)) {
                task.setStatus(TaskStatus.ATRASADA);
            } else if (task.getDueDate().isEqual(currentDate)) {
                task.setStatus(TaskStatus.PREVISTA);
            } else {
                task.setStatus(TaskStatus.CONCLUIDA);
            }
        } else if (task.getType() == TaskType.PRAZO) {

        } else {
            if (task.getCompleted()) {
                task.setStatus(TaskStatus.CONCLUIDA);
            } else {
                task.setStatus(TaskStatus.PREVISTA);
            }
        }
    }
}