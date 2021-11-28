package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class messagesTestClass {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args){
        messages a = new messages("Hi", null, "SenderID", "ReceiverID", 9, ".pdf", 0 );


        System.out.println(a);
        System.out.println("a.equals(a) returns " +a.equals(a));
        System.out.println("The object has an attachment is "  + a.hasAttachment());
        System.out.println(a.toByteArray().toString());

    }

    public static void DeSerialize(byte[] yourBytes){
        ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                // ignore close exception
            }
        }

    }
}
