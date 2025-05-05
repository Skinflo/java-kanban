package manager;

import org.junit.jupiter.api.Test;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtackTest {

    @Test
    public void subtaskEqual() {
        Subtask subtask1 = new Subtask(1, "1", "1", TaskStatus.NEW, 1);
        Subtask subtask2 = new Subtask(1, "2", "2", TaskStatus.NEW, 2);
        assertEquals(subtask1, subtask2);
    }
}
