package model;
import model.enums.Priority;
import model.enums.Status;

public class Task {
    private int id;
    private String title;
    private Status status;
    private Priority priority;


    public Task (int id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
        this.priority = Priority.MEDIUM;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
