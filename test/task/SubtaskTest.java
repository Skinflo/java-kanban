package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void equals_returnTrue_idsAreSame() {
        Subtask subtask1 = new Subtask(1, "1", "1", TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask(1, "2", "2", TaskStatus.NEW, 2);
        assertTrue(subtask1.equals(subtask2));
    }
}