package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {
    @Test
    public void equals_returnTrue_idsAreSame() {
        Subtask subtask1 = new Subtask(1, "1", "1", TaskStatus.NEW, 0);
        Subtask subtask2 = new Subtask(1, "2", "2", TaskStatus.NEW, 0);
        assertTrue(subtask1.equals(subtask2));
    }

    @Test
    public void setId_notSetValue_idSameWithEpicId() {
        Subtask subtask = new Subtask(1,"1","1",TaskStatus.NEW,0);

        subtask.setId(0);
        assertEquals(1,subtask.getId());

        subtask.setId(5);
        assertEquals(5,subtask.getId());
    }
}