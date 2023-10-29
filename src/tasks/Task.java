package tasks;

public class Task {
    private int id;
    protected TaskTypes type;
    protected String taskName;
    protected Status status;
    protected String description;


    protected int epic;
    protected Status statusOfTask = Status.NEW;

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = statusOfTask;
        this.type = TaskTypes.TASK;
    }

    public Task(String taskName, String description, int epic) {
        this.epic = epic;
        this.taskName = taskName;
        this.description = description;
        this.status = statusOfTask;
        this.type = TaskTypes.TASK;
    }


    public Task(int id, String taskName, Status status, String description, int epic) {
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

    public int getEpic() {
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
                ", statusOfTask: " + status;
    }

    public String toStringFromFile() {
        return String.format("%s,%s,%s,%s,%s,%s", id, type, taskName, status, description, epic);
    }

    public Status getStatusOfTask() {
        return statusOfTask;
    }


}
