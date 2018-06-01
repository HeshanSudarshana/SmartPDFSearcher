package business_logic;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataFlowManagerTest {

    @Test
    public void login() {
        DataFlowManager.getInstance().login("user", 1, "/workspace/");
        assertTrue(DataFlowManager.getInstance().getUsername()!=null);
    }

    @Test
    public void logout() {
        DataFlowManager.getInstance().login("user", 1, "/workspace/");
        DataFlowManager.getInstance().logout();
        assertTrue(DataFlowManager.getInstance().getUsername()==null);
    }
}