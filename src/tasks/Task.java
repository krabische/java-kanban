package tasks;

public class Task {
    private int id;
    protected TaskTypes type;
    protected String taskName;
    protected Status status;
    protected String description;


    protected String epic;
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

    public Task(int id, String taskName, Status status, String description, String epic) {
        this.id = id;
        this.type = TaskTypes.TASK;
        this.taskName = taskName;
        this.status = status;
        this.description = description;
        this.epic = epic;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public TaskTypes getType() {
        return type;
    }

    public Status getStatus() {
        return status;
    }

    public String getEpic() {
        return epic;
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

    public String toStringFromFile() {
        return String.format("%s,%s,%s,%s,%s,%s", id, type, taskName, status, description, "");
    }

    public Status getStatusOfTask() {
        return statusOfTask;
    }


}
