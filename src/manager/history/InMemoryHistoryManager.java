package manager.history;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    @Override
    public void add(Task task) {
        Task taskForHistory = null;
        try {
            taskForHistory = (Task) task.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        linkLast(taskForHistory);
    }

    @Override
    public void remove(int id) {
        removeNode(nodesMap.get(id));
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private final Map<Integer, Node> nodesMap = new HashMap<>();
    private Node head;
    private Node tail;

    private void linkLast(Task task) {
        Node element = new Node();
        element.setTask(task);

        if (nodesMap.containsKey(task.getId())) {
            removeNode(nodesMap.get(task.getId()));
        }
        if (head == null) {
            tail = element;
            head = element;
            element.setNext(null);
            element.setPrev(null);
        } else {
            element.setPrev(tail);
            element.setNext(null);
            tail.setNext(element);
            tail = element;
        }
        nodesMap.put(task.getId(), element);
    }

    private List<Task> getTasks() {
        List<Task> tasksList = new ArrayList<>();
        Node element = head;
        while (element != null) {
            tasksList.add(element.getTask());
            element = element.getNext();
        }
        return tasksList;
    }

    private void removeNode(Node node) {
        if (node != null) {
            nodesMap.remove(node.getTask().getId());
            Node next = node.getNext();
            Node prev = node.getPrev();

            if (head == node) {
                head = node.getNext();
            }
            if (tail == node) {
                tail = node.getPrev();
            }
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrev(prev);
            }
        }
    }

    class Node {
        private Task task;
        private Node next;
        private Node prev;

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
