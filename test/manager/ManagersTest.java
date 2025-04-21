package manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    public void ShouldReturnTrueWhenGetDefaultReturnNotNull() {
        assertNotNull(Managers.getDefault(), "возвращает null");
    }

    @Test
    public void ShouldReturnTrueWhenGetDefaultHistoryReturnNotNull() {
        assertNotNull(Managers.getDefaultHistory(), "возвращает null");
    }
}