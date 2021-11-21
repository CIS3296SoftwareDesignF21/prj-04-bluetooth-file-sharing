package com.example.app.messages;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class messagesTestClass {
    public static void main(String[] args) {
         String FilePath = "This is the message";//realy just a stand in for the name of anything recieved over bluetooth
         String sender = "Sender ID";//id of device sending the packet
         String target = "Reciever ID";//id of device sending the packet
         String FileType = "pdf";
         int timeRecievd = 11162001; //figure out what format these are returned in =
         int sizeInMem = 5186;//in some standard unit


        //make a for loop and create and add a new messgae to the array list each time by modifying each one per iteration
        //test what ArrayList fnctions are able to work
        //in check benefits of making an object comparable and benefits of making implementing Comparator in an object to know what you are missing.
        //Take a look at Nicks work to see what an actual "Message Looks like rn"
        //Work with Zack to see how he wants the object stored
        //

      messages rep[] =new messages[4];

      for(int i = 0; i < rep.length; i++){
          //messages(String FilePath, String sender, String target, int timeReceived, String FileType, int sizeInMem)
          rep[i] = new messages(FilePath , sender, target, timeRecievd, FileType, sizeInMem);
      }

      for(int i = 0; i < rep.length; i++){
          System.out.println(rep[i]);
      }






    }
}
