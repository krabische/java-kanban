package managers;

public class Trash {

        /*  case 1:
                    ArrayList<Task> tasks = fileBackedTasksManager.getAllTasks();
                    System.out.println("Задачи:");
                    for (Task tasken : tasks) {
                        System.out.println(tasken.toString());
                    }
                    ArrayList<Epic> epics = fileBackedTasksManager.getAllEpics();
                    for (Epic epicen : epics) {
                        System.out.println(epicen.toString());
                        System.out.println("    Подзадачи:");
                        for (Integer subtaskId : epicen.getSubtaskIds()) {
                            Subtask subtasken = fileBackedTasksManager.getSubtasks().get(subtaskId);
                            System.out.println("    " + subtasken.toString());
                        }
                    }
                    break; */
                /*case 2:
                    System.out.println("Задачи созданы!");
                    task = new Task("Убраться", "помыть полы");
                    fileBackedTasksManager.putTask(task);
                    task = new Task("Погулять", "увидеться с Вовой");
                    fileBackedTasksManager.putTask(task);
                    epic = new Epic("Починить телевизор", "сломался экран");
                    fileBackedTasksManager.putEpic(epic);
                    epic = new Epic("Купить собаку", "найти продавца и договориться о встречи");
                    fileBackedTasksManager.putEpic(epic);
                    break; */
               /* case 3:
                    System.out.println("Подзадачи созданы!");
                    epicId = 3;
                    subtask = new Subtask("Шурупы", "Купить шурупы", epicId);
                    fileBackedTasksManager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей домик", "на сайте", epicId);
                    fileBackedTasksManager.putSubtask(subtask);
                    epicId = 4;
                    subtask = new Subtask("Купить ей корм", "на рынке", epicId);
                    fileBackedTasksManager.putSubtask(subtask);
                    break; */

    /*
     public ArrayList<String> readFileContents(String fileName) {
        String path = "/Users/krabische/dev/java-kanban/src/resources/AllTasks.csv";
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    public ArrayList<Task> splitByTasks(String fileName){
        ArrayList<String> content = readFileContents(fileName);
        for (int i = 1; i < content.size(); i++) {
            String line = content.get(i);
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            TaskTypes type = TaskTypes.valueOf(parts[1]);
            String name = parts[2];
            Status status = Status.valueOf(parts[3]);
            String description = parts[4];
            int epic = Integer.parseInt(parts[5]);
            Task task = new Task(id,  name, status, description, epic);
            tasksFromReader.add(task);
        }
        return tasksFromReader;
    }
     */
}
