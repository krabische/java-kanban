package AllTasks;

public class Task {
    private int id;
    protected String taskName;
    protected String description;
    protected Status statusOfTask = Status.NEW;

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
    }

    public Task(int id, String taskName, String description, Status statusOfTask) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.statusOfTask = statusOfTask;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatusOfTask(Status statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    @Override
    public String toString() {
        return "№" + id + " " + taskName +
                ", описание: " + description +
                ", statusOfTask: " + statusOfTask;
    }

    public Status getStatusOfTask() {
        return statusOfTask;
    }
}
