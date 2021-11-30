package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Objects;
import java.util.ArrayList;
public class messages_db {

    private ArrayList<messages> message_db_rec = new ArrayList<messages>();

    private ArrayList<messages> message_db_sent = new ArrayList<messages>();

    public messages_db(){
        ArrayList<messages> message_db_sent;
        ArrayList<messages> message_db_rec;
    }

    private messages store_rec_message(messages message){
        message_db_rec.add(message);
        return message;
    }
    private messages store_sent_message(messages message){
        message_db_sent.add(message);
        return message;
    }
}
