package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    public void equals_returnTrue_idsAreSame() {
        Task task1 = new Task(1, "1", "1", TaskStatus.NEW);
        Task task2 = new Task(1, "2", "2", TaskStatus.NEW);
        assertTrue(task1.equals(task2));
    }
}