package com.example.task.presentation;

import com.example.task.models.Task;
import com.example.task.services.TaskService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "taskBean")
@SessionScoped

public class TaskBean implements Serializable {


    /// /editingtask vaule
//    // This holds the ID of the task currently being edited (null if no task is being edited)
//    private Integer editingTaskId;


    // This is our service class that contains the business logic
    private final TaskService service = new TaskService();

    // This field stores the temporary text when editing a task
//    private String editValue;
    // This field stores the value typed by user in the "Add Task" input box
    private String newTask;
    private Task editingTask;

    // Getter and setter for the "Add Task" input field
    public String getNewTask() {
        return newTask;
    }

    public void setNewTask(String newTask) {
        this.newTask = newTask;
    }

    // Getter and setter for the editing input field
//    public String getEditValue() {
//        return editValue;
//    }
//
//    public void setEditValue(String editValue) {
//        this.editValue = editValue;
//    }
//
//    public Integer getEditingTaskId() {
//        return editingTaskId;
//    }

    public boolean isEditingTask(int id) {
        return editingTask != null && editingTask.getId() == id;

    }


    /**
     * This method adds a new task.
     * It sends the text entered by the user to the service.
     */
    public String addTask() {

        service.add(newTask);   // add to list
        newTask = "";           // clear the input field
        return null;            // stay on same page
    }

    /**
     * This deletes a task by its ID.
     */
    public String deleteTask(int id) {
        service.delete(id);
        return null;
    }

    /**
     * This toggles a task’s status between completed and pending.
     */
    public String toggleTaskCompletion(int id) {
        service.toggleComplete(id);
        return null;
    }

    public Task getEditingTask() {
        return editingTask;
    }

    public void setEditingTask(Task editingTask) {
        this.editingTask = editingTask;
    }

    /**
     * Called when user clicks "Edit".
     * We store the task ID and name to display in the input box.
     */


    public void startEdit(int id) {
        editingTask = service.getTaskById(id);
    }
    public String saveEdit() {
        if (editingTask != null) {
            service.updateTask(editingTask.getId(), editingTask.getName());
            editingTask = null; // Exit editing mode
        }
        return null;
    }


//    public String startEdit(int id) {
//        Task task = service.getTaskById(id);
//        if (task != null) {
//            editingTaskId = id;
//            editValue = task.getName();
//        }
//        return null;
//    }

    /**
     * This method saves the updated name to the task.
     */
//    public String saveEdit() {
//        if (editingTaskId != null) {
//            service.updateTask(editingTaskId, editValue);
//            editingTaskId = null;  // clear edit mode
//            editValue = null;
//        }
//        return null;
//    }

    /**
     * This cancels the editing operation.
     */
    public String cancelEdit() {
        editingTask = null;

        return null;
    }

    /**
     * Clears all tasks and resets ID counter.
     */
    public String clearTasks() {
        service.clearTasks();
        return null;
    }


    // Returns only pending tasks
    public List<Task> getTasksPending() {
        return service.getPendingTasks();
    }

    // Returns only completed tasks
    public List<Task> getTasksCompleted() {
        return service.getCompletedTasks();
    }
}
