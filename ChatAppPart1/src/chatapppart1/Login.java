package chatapppart1; 

public class Login {

    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor 
    public Login(String username, String password, String cellPhoneNumber) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // --- CHECK USERNAME ---
    public boolean checkUserName() {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        }
        return false;
    }

    // --- CHECK PASSWORD ---
    public boolean checkPasswordComplexity() {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // --- CHECK CELL PHONE NUMBER ---
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() == 12) {
            String digits = cellPhoneNumber.substring(1);
            for (char c : digits.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
            return true;
        }
        return false;
    }

    // --- REGISTER USERNAME (this is what Main.java calls) ---
    public String registerUsername() {
        if (checkUserName()) {
            return "Username successfully captured";
        } else {
            return "Username is not correctly formatted; please ensure that your " +
                   "username contains an underscore and is no more than five characters in length";
        }
    }

    // --- REGISTER PASSWORD (this is what Main.java calls) ---
    public String registerPassword() {
        if (checkPasswordComplexity()) {
            return "Password successfully captured";
        } else {
            return "Password is not correctly formatted";
        }
    }

    // --- REGISTER CELL PHONE NUMBER (this is what Main.java calls) ---
    public String registerCellPhoneNumber() {
        if (checkCellPhoneNumber()) {
            return "Cell phone number successfully captured";
        } else {
            return "Cell phone number incorrectly formatted or does not contain an international code";
        }
    }
}