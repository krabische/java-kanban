package resources;

import tasks.*;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileReader extends Reader {

    ArrayList<Task> tasksFromReader = new ArrayList<>();

    public FileReader(String fileName) {
    }

    public ArrayList<String> readFileContents(String fileName) {
        String path = "./resources/" + fileName;
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
            String epic = parts[5];
            Task task = new Task(id,  name, status, description, epic);
            tasksFromReader.add(task);
        }
        return tasksFromReader;
    }


    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
    }
}