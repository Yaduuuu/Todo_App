package com.todo.todo_app.service;

import com.todo.todo_app.models.Task;
import com.todo.todo_app.repository.TaskRepository;

import java.util.List;

public class TaskService {

    private final TaskRepository repository = new TaskRepository();

    public void add(String name) {
        if (name != null && !name.trim().isEmpty()) {
            repository.save(new Task(name.trim()));
        }
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public void toggleComplete(int id) {
        Task task = repository.findById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
        }
    }

    public void updateTask(int id, String newName) {
        Task task = repository.findById(id);
        if (task != null && newName != null && !newName.trim().isEmpty()) {
            task.setName(newName.trim());
        }
    }

    public void clearTasks() {
        repository.clear();
    }

    public Task getTaskById(int id) {
        return repository.findById(id);
    }

    public List<Task> getPendingTasks() {
        return repository.findPending();
    }

    public List<Task> getCompletedTasks() {
        return repository.findCompleted();
    }
}

