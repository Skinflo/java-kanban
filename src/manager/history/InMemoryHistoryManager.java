package manager.history;

import task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private final ArrayList<Task> tasksHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (tasksHistory.size() >= 10) {
            tasksHistory.remove(0);
        }
        Task taskForHistory = null;
        try {
            taskForHistory = (Task) task.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        tasksHistory.add(taskForHistory);
    }

    @Override
    public ArrayList<Task> getHistory() {
        return tasksHistory;
    }
}
