// Unit testing suite for assignment verification
package com.quickchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageManagerTest {

    @Test
    public void testLongestMessage() {
        MessageManager.populateTestData();
        assertEquals("Where are you? You are late! I have asked you to be on time.", MessageManager.getLongestMessage());
    }
    @Test
    public void testSearchRecipient() {
        MessageManager.populateTestData();
    String expected =  "Where are you? You are late! I have asked you to be on time.\n"
            + "Ok, I am leaving without you.";
           
    assertEquals(expected, MessageManager.searchRecipient("+27838884567"));
           
    }  
  @Test
public void testSearchMessageID() {
    MessageManager.populateTestData();
     String id =
            MessageManager.sentMessages.get(0).getMessageID();
     String expected =
            "Recipient: +27834557896\nMessage: Did you get the cake?";
     String actual =
            MessageManager.searchMessageID(id);

    assertEquals(expected, actual);
}
@Test
public void testDeleteMessageHash() {
    MessageManager.populateTestData();
     String hash =
            MessageManager.storedMessages.get(0).getMessageHash();
     String expected =
            "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
     String actual =
            MessageManager.deleteMessageByHash(hash);

    assertEquals(expected, actual);
}
@Test
public void testDisplayReport() {

    MessageManager.populateTestData();

    String report =
            MessageManager.displayReport();

    assertTrue(report.contains("Message Hash:"));
    assertTrue(report.contains("Recipient:"));
    assertTrue(report.contains("Message:"));
    assertTrue(report.contains("Did you get the cake?"));
    assertTrue(report.contains("It is dinner time!"));
}
@Test
public void testArraysPopulated() {

    MessageManager.populateTestData();

    assertEquals(2, MessageManager.sentMessages.size());
    assertEquals(2, MessageManager.storedMessages.size());
    assertEquals(1, MessageManager.disregardedMessages.size());

    assertEquals(5, MessageManager.messageHashes.size());
    assertEquals(5, MessageManager.messageIDs.size());
}
}
