// Core logic handler for storing, deleting, and printing messages
package com.quickchat;

import java.util.ArrayList;

public class MessageManager {

    // Making the arraylists to hold all our message objects and data
    public static java.util.ArrayList<Message> sentMessages = new java.util.ArrayList<>();
    public static java.util.ArrayList<Message> storedMessages = new java.util.ArrayList<>();
    public static java.util.ArrayList<Message> disregardedMessages = new java.util.ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    
    // Method to load some mock dummy data so we can test if things work
    public static void populateTestData() {
        // Clear everything first so we don't duplicate data if called twice
        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();

        // Making the hardcoded message objects from assignment instructions
        Message m1 = new Message(1, "+27834557896", "Did you get the cake?");
        Message m2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.");
        Message m4 = new Message(4, "0838884567", "It is dinner time!");
        Message m5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");

        // Sorting them into the right lists manually
        sentMessages.add(m1);
        sentMessages.add(m4);

        storedMessages.add(m2);
        storedMessages.add(m5);

        disregardedMessages.add(m3);
        
        // Getting the hashes and adding them to our hash tracking list
        messageHashes.add(m1.getMessageHash());
        messageHashes.add(m2.getMessageHash());
        messageHashes.add(m3.getMessageHash());
        messageHashes.add(m4.getMessageHash());
        messageHashes.add(m5.getMessageHash());

        // Getting the IDs and adding them to our ID tracking list
        messageIDs.add(m1.getMessageID());
        messageIDs.add(m2.getMessageID());
        messageIDs.add(m3.getMessageID());
        messageIDs.add(m4.getMessageID());
        messageIDs.add(m5.getMessageID());
    }

    // Loops through stored list to find whichever text is the longest line
    public static String getLongestMessage() { 
        String longest = "";
        for (Message msg : storedMessages) {
            // Compare lengths to see if current one is bigger than what we saved
            if (msg.getMessageText().length() > longest.length()) {
                longest = msg.getMessageText();
            }
        }
        return longest;
    }

    // Looks for all messages that match a specific cell number
    public static String searchRecipient(String recipient) {
        String results = "";
        for (Message msg : storedMessages) {
            // Using .equals because strings can't use ==
            if (msg.getRecipient().equals(recipient)) {
                results += msg.getMessageText() + "\n";
            }
        }
        return results.trim(); 
    }

    // Checking both arrays to look for a specific message ID number
    public static String searchMessageID(String messageID) {
        // First check the sent messages array
        for (Message msg : sentMessages) {
            String currentID = "" + msg.getMessageID(); // Convert integer to string easily
            if (currentID.equals(messageID)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
        
        // Then check the stored messages array if it wasn't in sent
        for (Message msg : storedMessages) {
            String currentID = "" + msg.getMessageID();
            if (currentID.equals(messageID)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
        
        // Return this if neither loop found a match
        return "Message ID not found.";
    }
    
    // Finds a message with the unique hash string and drops it from the list
    public static String deleteMessageByHash(String hash) {
        // Normal for loop because we need the index 'i' to use the remove method
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                // Save the text before deleting so we can show it in the printout
                String deletedMessage = storedMessages.get(i).getMessageText();
                storedMessages.remove(i);

                return "Message: \"" + deletedMessage + "\" successfully deleted.";
            }
        }

        return "Message hash not found.";
    }
    
    // Merges sent messages details into a neat string report block
    public static String displayReport() {
        String report = "";
        for (Message msg : sentMessages) {
            // Just linking everything together with standard labels and breaks
            report += "Message Hash: " + msg.getMessageHash()
                    + "\nRecipient: " + msg.getRecipient()
                    + "\nMessage: " + msg.getMessageText()
                    + "\n\n";
        }
        return report.trim();
    }
    
    // Writes the array data out to a raw JSON structured file manually
    static void saveToJSONFile() {
        // Target file path name
        java.io.File file = new java.io.File("chat_storage.json");
        
        try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
            // Writing the top brackets of the JSON formatting
            writer.println("{");
            writer.println("  \"storedMessagesCount\": " + storedMessages.size() + ",");
            writer.println("  \"messages\": [");
            
            // Loop through all items and print out the keys and values
            for (int i = 0; i < storedMessages.size(); i++) {
                Message m = storedMessages.get(i);
                writer.println("    {");
                writer.println("      \"id\": \" " + m.getMessageID() + "\",");
                writer.println("      \"recipient\": \"" + m.getRecipient() + "\",");
                writer.println("      \"text\": \"" + m.getMessageText() + "\"");
                
                // If it is the last item in the loop, don't put a trailing comma
                if (i < storedMessages.size() - 1) {
                    writer.println("    },");
                } else {
                    writer.println("    }");
                }
            }
            
            // Close up JSON syntax brackets
            writer.println("  ]");
            writer.println("}");
            System.out.println("\n[SUCCESS] Saved data to: " + file.getAbsolutePath());
            
        } catch (java.io.IOException e) {
            // Basic catch printout if things go wrong or file is locked
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
