package managers;

import tasks.*;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager { //работает с хистори задач


    // private final List<Task> calledTasks = new ArrayList<>();
    Map<Integer, Node<Task>> historyMap = new HashMap<>();


    @Override
    public void add(Task task) {
        if (historyMap.containsKey(task.getId())) {
            Node<Task> nodeToRemove = historyMap.get(task.getId());
            removeNode(nodeToRemove);
            historyMap.remove(task.getId());
        }
        if (size >= 10) {
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

    public Node<Task> head;
    public Node<Task> tail;
    public int size = 0;

    public void linkLast(Task element) {
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

    public void removeNode(Node<Task> nodeToRemove) {
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

    public List<Task> getTasks() {
        List<Task> nodeTasks = new ArrayList<>();
        Node<Task> current = head;
        while (current != null) {
            nodeTasks.add(current.data);
            current = current.next;
        }
        return nodeTasks;
    }

}


