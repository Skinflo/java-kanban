package manager;

import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void addInHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        historyManager.add(new Task("1", "1"));
        assertNotNull(historyManager.getHistory());
    }
}