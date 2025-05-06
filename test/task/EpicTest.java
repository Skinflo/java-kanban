package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {
    @Test
    public void equals_returnTrue_idsAreSame() {
        Epic epic1 = new Epic(1, "1", "1");
        Epic epic2 = new Epic(1, "2", "2");
        assertTrue(epic1.equals(epic2));
    }

}