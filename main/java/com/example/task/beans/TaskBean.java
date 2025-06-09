package com.example.task.beans;

import com.example.task.models.Task;
import com.example.task.services.TaskService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "taskBean")
@SessionScoped
public class TaskBean {

    // Stores input from "Add Task" field
    private String newTask;

    // Stores temporary value when editing a task
    private String editValue;

    // Stores ID of the task currently being edited
    private Integer editingTaskId;

    // Reference to service layer
    private final TaskService service = new TaskService();

    // Getter & Setter for new task input
    public String getNewTask() {
        return newTask;
    }

    public void setNewTask(String newTask) {
        this.newTask = newTask;
    }

    // Getter & Setter for edit input
    public String getEditValue() {
        return editValue;
    }

    public void setEditValue(String editValue) {
        this.editValue = editValue;
    }

    // Add a new task
    public String addTask() {
        service.add(newTask);
        newTask = ""; // clear input
        return null;
    }

    // Delete a task by ID
    public String deleteTask(int id) {
        service.delete(id);
        return null;
    }

    // Toggle completion status
    public String toggleTaskCompletion(int id) {
        service.toggleComplete(id);
        return null;
    }

    // Start editing task by setting the ID and loading its current name
    public String startEdit(int id) {
        Task task = service.getTaskById(id);
        if (task != null) {
            editingTaskId = id;
            editValue = task.getName();
        }
        return null;
    }

    // Save edited name to task
    public String saveEdit() {
        if (editingTaskId != null) {
            service.updateTask(editingTaskId, editValue);
            editingTaskId = null;
            editValue = null;
        }
        return null;
    }

    // Cancel edit mode
    public String cancelEdit() {
        editingTaskId = null;
        editValue = null;
        return null;
    }

    // Clear all tasks
    public String clearTasks() {
        service.clearTasks();
        return null;
    }

    // For rendering tasks in the UI
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    public List<Task> getTasksPending() {
        return service.getPendingTasks();
    }

    public List<Task> getTasksCompleted() {
        return service.getCompletedTasks();
    }

    public Integer getEditingTaskId() {
        return editingTaskId;
    }

}
