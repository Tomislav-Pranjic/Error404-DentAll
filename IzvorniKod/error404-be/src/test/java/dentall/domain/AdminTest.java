package dentall.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    Admin adminDef;
    Admin adminTest;

    @BeforeEach
    @DisplayName("Setting up info")
    void setUp(){
        adminDef = new Admin();
        adminDef = new Admin("tomi", "password");
    }

    @Test
    @DisplayName("Testing w default info")
    void getAdminInfoDef() {
        assertNull(adminDef.getUserName(), "Username should be null for default constructor");
        assertNull(adminDef.passwordHashForAuth(), "Passwordhash should be null for default constructor");
    }

    @Test
    @DisplayName("Testing w testing info")
    void getAdminInfoTest() {
        assertEquals("tomi", adminTest.getUserName(), "Getter should return correct username");
        assertEquals("pass", adminTest.passwordHashForAuth(), "Getter should return correct passwordhash");
    }
}