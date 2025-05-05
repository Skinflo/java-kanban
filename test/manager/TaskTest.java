package manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import task.Task;
import task.TaskStatus;

public class TaskTest {

    @Test
    public void taskEqual() {
        Task task1 = new Task(1, "1", "1", TaskStatus.NEW);
        Task task2 = new Task(1, "2", "2", TaskStatus.NEW);
        assertEquals(task1, task2);
    }
}
