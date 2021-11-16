package com.example.app.messages;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class messagesTestClass {
    public static void main(String[] args) {
         String text = "This is the message";//realy just a stand in for the name of anything recieved over bluetooth
         String sender = "Sender ID";//id of device sending the packet
         String target = "Reciever ID";//id of device sending the packet
         String FileType = "pdf";
         int timeRecievd = 11162001; //figure out what format these are returned in =
         int sizeInMem = 5186;//in some standard unit



        messages TestMessgae = new messages(text, sender, target,timeRecievd, FileType, sizeInMem);
        //make a for loop and create and add a new messgae to the array list each time by modifying each one per iteration
        //test what ArrayList fnctions are able to work
        //in check benefits of making an object comparable and benefits of making implementing Comparator in an object to know what you are missing.
        //Take a look at Nicks work to see what an actual "Message Looks like rn"
        //Work with Zack to see how he wants the object stored
        //


        ArrayList<messages> works = new ArrayList<>();





    }
}
