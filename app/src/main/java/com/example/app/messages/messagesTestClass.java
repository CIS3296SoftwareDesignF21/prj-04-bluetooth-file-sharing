package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.app.messages.messages.toByteArray;

public class messagesTestClass {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args){
        messages a = new messages("Hi", null, "SenderID", "ReceiverID", 9, ".pdf", 0 );


        System.out.println(a);
        System.out.println("a.equals(a) returns " +a.equals(a));
        System.out.println("The object has an attachment is " + a.hasAttachment());
        System.out.println();

    }

    public static Object DeSerialize(byte[] yourBytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            System.out.println(o + " is the result of the byte array");// the result of the deserializer
            return o;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return null;

    }
}