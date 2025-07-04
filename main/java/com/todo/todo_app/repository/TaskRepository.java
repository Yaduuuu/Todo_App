package com.todo.todo_app.repository;

import com.todo.todo_app.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        tasks.add(task);
    }

    public void deleteById(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                break;
            }
        }
    }

    public Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> findCompleted() {
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }

    public List<Task> findPending() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }

    public void clear() {
        tasks.clear();
        Task.setIdCounter(1);
    }
}
