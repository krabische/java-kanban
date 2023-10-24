package managers;

import tasks.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager { //работает с хистори задач

    public InMemoryHistoryManager() {
        this.historyMap = new HashMap<>();
    }

    private Map<Integer, Node<Task>> historyMap;
    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;

    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("Task is null");
            return;
        }

        if (historyMap.containsKey(task.getId())) {
            Node<Task> nodeToRemove = historyMap.get(task.getId());
            removeNode(nodeToRemove);
            historyMap.remove(task.getId());
        }
        if (size >= 10 && head != null) {
            Task oldestTask = head.data;
            Node<Task> nodeToRemove = head;
            historyMap.remove(oldestTask.getId());
            removeNode(nodeToRemove);
        }
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            removeNode(historyMap.get(id));
            historyMap.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private void linkLast(Task element) {
        final Node<Task> oldTail = tail;
        Node<Task> newNode = new Node<>(oldTail, element, null);
        tail = newNode;
        historyMap.put(element.getId(), newNode);
        if (oldTail == null)
            head = newNode;
        else
            oldTail.next = newNode;
        size++;
    }

    private void removeNode(Node<Task> nodeToRemove) {
        if (head == nodeToRemove) {
            head = nodeToRemove.next;
            if (head != null) {
                head.prev = null;
            }
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
            }
        }

        if (tail == nodeToRemove) {
            tail = nodeToRemove.prev;
        }

        size--;
    }

    private List<Task> getTasks() {
        List<Task> nodeTasks = new ArrayList<>();
        Node<Task> current = head;
        while (current != null) {
            nodeTasks.add(current.data);
            current = current.next;
        }
        return nodeTasks;
    }

    class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(Node<T> prev, T data, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }


}


