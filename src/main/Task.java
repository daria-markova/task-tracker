package main;

public class Task {
    private int id;
    private String title;
    private boolean done;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return done;
    }

    public Task (int id, String title) {
        this.id = id;
        this.title = title;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }
}
