
package chatapppart1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Welcome to ChatApp Registration ===");
        System.out.println("");

        // Get username
        System.out.print("Enter your username: ");
        String username = input.nextLine();

        // Get password
        System.out.print("Enter your password: ");
        String password = input.nextLine();

        // Get cell phone number
        System.out.print("Enter your cell phone number (e.g. +27831234567): ");
        String cellNumber = input.nextLine();

        // Create Login object
        Login myLogin = new Login(username, password, cellNumber);

        // Print results
        System.out.println("");
        System.out.println(myLogin.registerUsername());
        System.out.println(myLogin.registerPassword());
        System.out.println(myLogin.registerCellPhoneNumber());
    }
}

