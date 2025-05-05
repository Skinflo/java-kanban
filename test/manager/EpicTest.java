package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    @Test
    public void EpicEqual() {
        Epic epic1 = new Epic(1, "1", "1");
        Epic epic2 = new Epic(1, "2", "2");
        assertEquals(epic1, epic2);
    }
}
