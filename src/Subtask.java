public class Subtask extends Task {
    public Subtask(String taskName, String description) {
        super(taskName, description);
    }

    public void setStatusOfSubtask(String statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

}
