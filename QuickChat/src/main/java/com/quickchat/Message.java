package com.quickchat;

import java.util.Random;

public class Message { 
    //Fileds required by the project guidelines
    private String messageID; 
    private int numMessagesSent;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    //Constructor to initialize a new message instance
    public Message(int numMessagesSent, String recipient, String messageText) {
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageText = messageText;
        
        //Automactially generate ID and hash when message is created
        this.messageID = generateRandomID();
        this.messageHash = createMessageHash();
        
    }
    
    //Helper method to create a random 10-digit tracking number
    private String generateRandomID() {
        Random rand = new Random();
        String result ="";
        
        //Loop 10 times to build a 10-digit numeric string
        for (int i = 0; i < 10; i++) {
            int randomDigit = rand.nextInt(10);
            result = result + randomDigit;
        }
        return result;
    }
    
    //1. Method to check if the message ID is valid 
    public boolean checkMessageID() {
        if (this.messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    // Validates formatting structure to ensure recipient numbers are properly categorized
    //2. Method to ensure recipient cell number is correctly formatted
    public String checkRecipientCell() { 
        //Simple validation checks to see if it starts with valid prefixes
        if (recipient.startsWith("+") || recipient.startsWith("0")) {
            if (recipient.length() <= 13) {
                return "Cell phone number successfully captured.";
                  
            }
        }
        return "Cell phone number is incorrectly formatted or does not exist. Please check the number and try again.";
    }

    // Verifcation rule ensuring the string length does not exceed assignment parameters
    //Helper validation method for checking message length limits
    public String verifyMessageLength() {
        if (this.messageText.length() <= 250) {
            return "Message ready to send.";
        } else{
            int extraCharacters = this.messageText.length() - 250;
            return "Message exceeds 250 characters by " + extraCharacters +"; please reduce the size.";
        }
    }
    
    //3. Method to autogenerate the captialized Message Hash
    public String createMessageHash() {
        //Step A: Extract first two characters of message ID
        String firstTwoDigits = this.messageID.substring(0, 2);
        
        //Step B: Extract first and last words of the text message
        String cleanText = this.messageText.trim();
        String[] words = cleanText.split(" ");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        //Step C: Remove trailing punctuation (like ? or .) from the last word if present
        if (lastWord.endsWith("?") || lastWord.endsWith(".") || lastWord.endsWith("!")) {
            lastWord = lastWord.substring(0, lastWord.length() - 1);
        }
        //Step D: Combine components using colon as required
        String combinedHash = firstTwoDigits + ":" + this.numMessagesSent + ":" + firstWord + lastWord;
        
        //Return the final result entirely in uppercase format
        return combinedHash.toUpperCase();
    }
    
    //4. Method allowing users to pick an action choice for their message
    public String SentMessage(int choice) {
        if (choice == 1) {
            return "Message successfully sent.";
        } else if (choice == 0) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) { 
            return "Message successfully stored.";
        }
        return "Invalid selection choice.";
    }
    
    //5. Method to arrange and print full layout details of a message
    public String printMessages() {
        String fullDetails = "Message ID: " + this.messageID + "\n" +
                             "Message Hash: " + this.messageHash + "\n" + 
                             "Recipient: " + this.recipient + "\n" +
                             "Message: " + this.messageText;
        return fullDetails;
    }
    
    //Getters needed to rend values inside our loops and unit tests
    public String getMessageID() {
        return this.messageID;
    }
    
    public String getMessageHash() {
        return this.messageHash;
    }
    
}
