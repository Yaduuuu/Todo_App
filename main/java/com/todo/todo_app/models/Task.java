package com.todo.todo_app.models;

public class Task {

    private static int idCounter = 1;
    private int id;
    private String name;
    private boolean completed;


    public Task(String name) {
        this.id = idCounter++;
        this.name = name;
    }


    public static void setIdCounter(int idCounter) {
        Task.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                '}';
    }
}
