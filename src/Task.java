public class Task {
    protected int id;
    protected String taskName;
    protected String description;
    protected String statusOfTask = "NEW \uD83C\uDD95";

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
    }

    public Task() {

    }

    public void setStatusOfTask(String statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    @Override
    public String toString() {
        return taskName +
                ", описание: " + description +
                ", статус: " + statusOfTask;
    }
}
