package com.quickchat;

import java.util.Scanner;

public class QuickChat { 
    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);
        
        //Setup a simple tracker variable for total sent messages
        int totalSentCount = 0;
        
        System.out.println("Welcome to QuickChat.");
        System.out.println("-----------------------------");
        
        //Assignment requirement 1: User must log in successfully first
        System.out.print("Please enter your Username to log in: ");
        String username = input.nextLine();
        System.out.print("Please enter your Password: ");
        String password = input.nextLine();
        
        System.out.println("\nLogin successful! Welcome, " + username + ".\n");
        
        //Assignment requirement 5: User sets max message limit at startup 
        System.out.print("How many messages do you wish to enter during this session? ");
        int maxMessages = input.nextInt();
        input.nextLine();//Cleans the scanner buffer memory 
        
        //Setup a beginner array structure to store created messages
        Message[] chatHistory = new Message[maxMessages];
        int currentMessageIndex = 0;
        
        //Assignment requirement 4: System loop runs until user chooses option 3 (Quit)
        int menuSelection = 0;
        
        while (menuSelection != 3) {
            //Displaying the numeric menu from your assignment sheet
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Quit");
            System.out.print("Please select an option (1-3): ");
            
            menuSelection = input.nextInt();
            input.nextLine();//Cleans the scanner buffer memory
            
            if (menuSelection == 1) {
                //Check if the user has hit their max session storage limit
                if (currentMessageIndex >= maxMessages) {
                    System.out.println("Error: You have reached your preset message limit of " + maxMessages);
                } else {
                    System.out.println("\n--- Create a Message ---");
                    System.out.println("Enter recipient cell phone number: ");
                    String cellNum = input.nextLine();
                    
                    System.out.print("Enter your message text: ");
                    String textContent = input.nextLine();
                    
                    //Construct the message object
                    Message tempMsg = new Message(totalSentCount, cellNum, textContent);
                    
                    //Display structural validations 
                    System.out.println("\n[System Check] ID Valid: " + tempMsg.checkMessageID());
                    System.out.println("[System Check] " + tempMsg.checkRecipientCell());
                    System.out.println("[System Check] " + tempMsg.verifyMessageLength());
                    
                    //Secondary menu workflow from your assignment rules
                    System.out.println("\nWhat would you like to do with this message?");
                    System.out.println("Select 1 -> Send Message");
                    System.out.println("Select 0 -> Disregard Message (Delete)");
                    System.out.println("Select 3 -> Store Message to send later");
                    System.out.print("Your choice: ");
                    int actionChoice = input.nextInt();
                    input.nextLine();
                    
                    // Prompt user for username credentials to access the console system menu
                    //Run action logic status response
                    String statusResult = tempMsg.SentMessage(actionChoice);
                    System.out.println("Status: " + statusResult);
                    
                    if (actionChoice == 1) {
                        //Keep message in array history and increment running system counters
                        chatHistory[currentMessageIndex] = tempMsg;
                        currentMessageIndex++;
                        totalSentCount++;
                    } else if (actionChoice == 3) {
                        //Stored message also get tracked in local history array space 
                        chatHistory[currentMessageIndex] = tempMsg;
                        currentMessageIndex++;
                    } else {
                        System.out.println("Message discarded and cleared from active process.");
                        
                    }
                    
                    
                    
                            
                }
            } else if (menuSelection == 2 ) {
                System.out.println("\n--- RECENTLY SENT MESSAGES ---");
                boolean foundAny = false;
                
                //Classic loop iteration pattern to print everything stored in array 
                for (int i = 0; i < currentMessageIndex; i++) {
                    if (chatHistory[i] != null) {
                        System.out.println("-------------------------------");
                        System.out.println(chatHistory[i].printMessages());
                        foundAny =true;
                        
                    }
                }
                
                if (!foundAny) {
                    System.out.println("No history found. This feature is still in development or empty.");
                    
                }
            } else if (menuSelection == 3) {
                //Requirement 6: Display total accumulated message summary upon exit execution
                System.out.println("\n-------------------------------");
                System.out.println("Total number of messages accumulated and sent: " + totalSentCount);
                System.out.println("Thank you for using QuickChat. Goodbye!");
                
            } else {
                System.out.println("Invalid menu choice selection. Please try again.");
            }
            
              
        }
        
        input.close();
        
    }
}
