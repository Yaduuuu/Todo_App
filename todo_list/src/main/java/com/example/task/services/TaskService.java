package com.example.task.services;

import com.example.task.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    // This list holds all tasks. It's like a temporary in-memory database.
    private final List<Task> tasks = new ArrayList<>();

//    private int nextId = 0;


    /**
     * Adds a new task with a unique ID.
     * We check that the name is not empty before adding.
     */
    public void add(String name) {
        if (name != null && !name.trim().isEmpty()) {
            Task task = new Task(name.trim());
//            task.setId(nextId++);
            tasks.add(task);
//            System.out.println("nextId = " + nextId);
            System.out.println("task = " + task);


        }
    }

    /**
     * Deletes a task by matching its ID.
     */
    public void delete(int id) {
//        tasks.remove(id);
        tasks.removeIf(task -> task.getId() == id);
        System.out.println("id = " + id);

    }

    /**
     * Toggles the completion status of a task by its ID.
     * If it was incomplete, it becomes completed and vice versa.
     */
    public void toggleComplete(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(!task.isCompleted());
                break;
            }
        }
    }

    /**
     * Updates the task name for the given ID.
     * We check that the new name is not empty.
     */
    public void updateTask(int id, String newName) {
        for (Task task : tasks) {
            if (task.getId() == id && newName != null && !newName.trim().isEmpty()) {
                task.setName(newName.trim());
                break;
            }
        }
    }

    /**
     * Clears all tasks and resets the ID counter.
     */
    public void clearTasks() {
        tasks.clear();    // Remove everything
        Task.setIdCounter(0);
//        nextId = 0;
    }

    /**
     * Returns a task by its ID.
     */
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }



    /**
     * Returns only pending tasks (not completed).
     */
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }

    /**
     * Returns only completed tasks.
     */
    public List<Task> getCompletedTasks() {
        return tasks.stream().filter(Task::isCompleted).collect(Collectors.toList());
//        List<Task> completed = new ArrayList<>();
//        for (Task task : tasks) {
//            if (task.isCompleted()) {
//                completed.add(task);
//                System.out.println("task = " + task);
//            }
//        }
//        return completed;
    }
}
