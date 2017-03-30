package model;

public class Library {
    private int id;
    private String title;
    private String author;
    private String inStore;

    public Library(){

    }

    public Library(int id, String title, String author, String inStore){
        this.id = id;
        this.title = title;
        this.author = author;
        this.inStore = inStore;
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

    public String getInStore() {
        return inStore;
    }

    public void setInStore(String inStore) {
        this.inStore = inStore;
    }
}
