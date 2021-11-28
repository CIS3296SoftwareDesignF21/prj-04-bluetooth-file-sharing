package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class messagesTestClass {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(String[] args) throws IOException {
        messages a = new messages("Hi", null, "SenderID", "ReceiverID", 011, ".pdf", 0 );

        System.out.println(a);
        System.out.println("a.equals(a) returns " +a.equals(a));
        System.out.println("The object has an attachment is "  + a.hasAttachment());
        a.toByteArray();
       /* byte[] holder = a.toByteArray();
        System.out.println(holder);*/




    }
}
