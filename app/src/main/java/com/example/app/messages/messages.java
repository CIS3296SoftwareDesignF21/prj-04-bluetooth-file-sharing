package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.Objects;

public class messages {


    public String text;//realy just a stand in for the name of anything recieved over bluetooth
    public String sender;//id of device sending the packet
    public String target;//id of device sending the packet
    public String FileType;
    public int timeRecievd;
    //message object will be sent over bT impl

    public messages(String text, String sender, String target, int timeRecievd, String FileType){
        this.text = text;
        this.sender = sender;
        this.target = target;
        this.FileType = FileType;
        this.timeRecievd = timeRecievd;
    }

    public boolean arrivedBefore(messages B){//returns true if the Recived Time of the current message is less than the
        //message passed as a parameter
        if(this.timeRecievd < B.timeRecievd){
            return true;
        }
        return false;
    }



    @Override
    public String toString() {//edit if any changes to the constructor is made
        return "messages{" +
                "text='" + text + '\'' +
                ", sender='" + sender + '\'' +
                ", target='" + target + '\'' +
                ", FileType='" + FileType + '\'' +
                ", timeRecievd=" + timeRecievd +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        messages messages = (messages) o;
        return timeRecievd == messages.timeRecievd && Objects.equals(text, messages.text) && Objects.equals(sender, messages.sender) && Objects.equals(target, messages.target) && Objects.equals(FileType, messages.FileType);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(text, sender, target, FileType, timeRecievd);
    }



}
