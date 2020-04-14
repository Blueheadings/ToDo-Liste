package com.example.todo_liste;

import androidx.annotation.NonNull;

public class Item {

    private String title;
    private String description;
    private String priority;
    private String date;

    public Item(String title, String description, String preference, String date) {
        this.title = title;
        this.description = description;
        this.priority = preference;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return this.title + "    " + this.description + "    " +  this.priority;
    }
}
