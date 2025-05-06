package manager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    public void getDefault_returnNotNull() {
        assertNotNull(Managers.getDefault());
    }

    @Test
    public void getDefaultHistory_returnNotNull() {
        assertNotNull(Managers.getDefaultHistory());
    }
}