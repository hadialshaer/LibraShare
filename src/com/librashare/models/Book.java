package com.librashare.models;

public class Book {
    private int id;
    private String title;
    private String description;
    private String author;
    private boolean isAvailable;
    private String category;
    private String location;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Book(int id, String title, String description, String author, String category, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
        this.location = location;
        this.isAvailable = true;
    }

    // Getters and setters for new fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
