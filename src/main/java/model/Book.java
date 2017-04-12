package model;

import java.io.Serializable;

public class Book implements Serializable{
    private static final long serialVersionUID = 6297385302078200511L;

    private int id;
    private String title;
    private String author;
    private String genre;

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(int id, String title, String author, String genre) {
        this(title, author, genre);
        this.id = id;
    }

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(float count) {
        this.genre = genre;
    }
}
