package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Hashtable;
import java.util.Objects;
import java.util.ArrayList;

public class messages_db {

    private Hashtable<String, ArrayList<String>> message_db_rec = new Hashtable<>();

    private Hashtable<String, ArrayList<String>> message_db_sent = new Hashtable<>();
    
    private latest_message;

    public messages_db(Hashtable<String, ArrayList<String>> hash_rec, Hashtable<String, ArrayList<String>> hash_sent, messages latest_message) {
        this.message_db_rec = hash_rec;
        this.message_db_sent = hash_sent;
        this.latest_message = latest_message;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void store_rec_message(messages message) {
        ArrayList<String> list = message_db_rec.get(message.sender);
        list.add(message.textContent);
        ArrayList<String> oldlist = message_db_rec.replace(message.sender, list);
        latest_message = message;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void store_sent_message(messages message) {
        ArrayList<String> list = message_db_sent.get(message.sender);
        list.add(message.textContent);
        ArrayList<String> oldlist = message_db_sent.replace(message.sender, list);
        latest_message = message;
    }

    private ArrayList<String> get_sent(String name) {
        ArrayList<String> texts = message_db_sent.get(name);
        return texts;
    }
    private ArrayList<String> get_rec(String name) {
        ArrayList<String> texts = message_db_rec.get(name);
        return texts;
    }

}
