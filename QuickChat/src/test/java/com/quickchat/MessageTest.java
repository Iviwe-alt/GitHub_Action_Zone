package com.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {
    
    //1. Test for message length under/at 250 characters 
    @Test //================== AUTOMATED VALIDATION TESTING SUITE =====================
    public void testVerifyMessageLengthSuccess() {
        Message msg = new Message(0, "+277186930002", "HI Mike, can you join us for dinner tonight?");
        String expected = "Message ready to send.";
        assertEquals(expected, msg.verifyMessageLength());
        
    }
    
    //2. Test for message length exceeding 250 characters
    @Test
    public void testVerifyMessageLengthFailure() {
        //Generates a text string that is intentionally 255 characters long
        String longText = "a" .repeat(255);
        Message msg = new Message(0, "+277186930002", longText);
        
        String expected = "Message exceeds 250 characters by 5; please reduce the size.";
        assertEquals(expected, msg.verifyMessageLength());
    }
    
    //3. Test for valid recipient number formatting 
    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message(0, "+277186930002", "Hello Mike");
        String expected = "Cell phone number successfully captured.";
        assertEquals(expected, msg.checkRecipientCell());
    }
    
    //4. Test for invalid recipient number formatting
    @Test 
    public void testCheckRecipientCellFailure() {
        Message msg = new Message(0, "ABC12345", "Hello Mike");
        String expected = "Cell phone number is incorrectly formatted or does not exist. Please check the number and try again.";
        assertEquals(expected, msg.checkRecipientCell());
                       
    }
    
    //5. Test to verify the automated Message Hash rules 
    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(0, "+277186930002", "HI Mike, can you join us for dinner tonight?");
        String actualHash = msg.getMessageHash();
        
        assertTrue(actualHash.contains("HI"));
        assertTrue(actualHash.contains("TONIGHT"));
    }
   
}
