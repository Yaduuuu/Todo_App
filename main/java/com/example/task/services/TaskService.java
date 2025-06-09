package com.example.task.services;

import com.example.task.models.Task;

import java.util.*;


public class TaskService {

    // Map to store tasks using ID as the key
//    private final Map<Integer, Task> tasks = new HashMap<>();
    // Use LinkedHashMap to preserve insertion order
    private final Map<Integer, Task> tasks = new LinkedHashMap<>();
//    private LinkedList<Task> tasks = new LinkedList<>();

    // Used to assign a unique ID to each new task
    private int nextId = 1;

    /**
     * Adds a new task to the map with a unique ID.
     */
    public void add(String name) {
        if (name != null && !name.trim().isEmpty()) {
            Task newTask = new Task(nextId, name.trim());
            tasks.put(nextId, newTask);
            nextId++;
        }
    }

    /**
     * Deletes a task by its ID.
     */
    public void delete(int id) {
        tasks.remove(id);
    }


//    public void toggleComplete(int id) {
//        Task task = tasks.get(id);
//        if (task != null) {
//            task.setCompleted(!task.isCompleted());
//        }
//    }
    public void toggleComplete(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            // Step 1: Remove it from map (and remember the task)
            tasks.remove(id);

            // Step 2: Toggle completion
            task.setCompleted(!task.isCompleted());

            // Step 3: Re-add it with same ID (puts it at the end of LinkedHashMap)
            tasks.put(id, task);
        }
    }


    /**
     * Updates the name of the task with the given ID.
     */
    public void updateTask(int id, String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            Task task = tasks.get(id);
            if (task != null) {
                task.setName(newName.trim());
            }
        }
    }

    /**
     * Removes all tasks and resets the ID counter.
     */
    public void clearTasks() {
        tasks.clear();
        nextId = 1;
    }

    /**
     * Returns a list of all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    /**
     * Returns only the tasks that are not completed.
     */
    public List<Task> getPendingTasks() {
        List<Task> pending = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (!task.isCompleted()) {
                pending.add(task);
            }
        }
        return pending;
    }

    /**
     * Returns only the completed tasks.
     */
    public List<Task> getCompletedTasks() {
        List<Task> completed = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.isCompleted()) {
                completed.add(task);
            }
        }
        return completed;
    }

    /**
     * Gets a task by its ID.
     */
    public Task getTaskById(int id) {
        return tasks.get(id);
    }
}
