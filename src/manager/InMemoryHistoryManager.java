package manager;

import task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private final ArrayList<Task> tasksHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (tasksHistory.size() >= 10) {
            tasksHistory.remove(0);
        }
        Task taskForHistory = new Task(task.getId(), task.getName(), task.getDescription(), task.getStatus());
        tasksHistory.add(taskForHistory);
        }

    @Override
    public ArrayList<Task> getHistory() {
        return tasksHistory;
    }
}
