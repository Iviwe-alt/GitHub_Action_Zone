import chatapppart1.Login;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    // Test 1: Valid username (has underscore, 5 chars or less)
    @Test
    public void testValidUsername() {
        Login login = new Login("Blow_", "Mess@525", "+27678900135");
        assertTrue(login.checkUserName());
    }

    // Test 2: Invalid username (no underscore)
    @Test
    public void testInvalidUsername() {
        Login login = new Login("Blow1", "Mess@525", "+27678900135");
        assertFalse(login.checkUserName());
    }

    // Test 3: Valid password
    @Test
    public void testValidPassword() {
        Login login = new Login("Blow_", "Mess@525", "+27678900135");
        assertTrue(login.checkPasswordComplexity());
    }

    // Test 4: Invalid password (no special character)
    @Test
    public void testInvalidPassword() {
        Login login = new Login("Blow_", "Password1", "+27678900135");
        assertFalse(login.checkPasswordComplexity());
    }

    // Test 5: Valid cell phone number
    @Test
    public void testValidCellNumber() {
        Login login = new Login("Blow_", "Mess@525", "+27678900135");
        assertTrue(login.checkCellPhoneNumber());
    }

    // Test 6: Invalid cell phone number (wrong format)
    @Test
    public void testInvalidCellNumber() {
        Login login = new Login("Blow_", "Mess@525", "0678900135");
        assertFalse(login.checkCellPhoneNumber());
    }
}
