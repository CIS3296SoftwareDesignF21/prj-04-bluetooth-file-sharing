package com.example.app.messages;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import kotlin.UByteArray;

import java.io.File;
import java.util.Objects;

public class messages {//message object will be sent over bT impl


    //public String FilePath;//really just a stand in for the name of anything received over bluetooth
    public String textContent = null;// text message that will be sent
    public Uri FileURI = null; //possible
    public UByteArray byteArray = null;
    public String sender = null;//id of device sending the packet
    public String target = null;//id of device sending the packet
    public int timeReceived  = 0;
    public String FileType = null;
    public int sizeInMem = 0; //referring to the messages size in memory


    public messages(String textContent, UByteArray byteArray, String sender, String target, int timeReceived, String FileType, int sizeInMem){//constructor form message with no attachment
        this.textContent  = textContent;
        this.byteArray = byteArray;
        this.sender = sender;
        this.target = target;
        this.timeReceived = timeReceived;
        this.FileType = FileType;
        this.sizeInMem = sizeInMem;
    }

    public messages(String textContent, Uri FileURI, UByteArray byteArray, String sender, String target, int timeReceived, String FileType, int sizeInMem){//for messages with a file attachment
        this.textContent = textContent;
        this.FileURI = FileURI;
        this.byteArray = byteArray;
        this.sender = sender;
        this.target = target;
        this.timeReceived = timeReceived;
        this.FileType = FileType;
        this.sizeInMem = sizeInMem;
    }



    public boolean arrivedBefore(messages B){//returns true if the Recived Time of the current message is less than the
        //message passed as a parameter
        if(this.timeReceived < B.timeReceived){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "messages{" +
                "textContent='" + textContent + '\'' +
                ", FileURI=" + FileURI +
                ", byteArray=" + byteArray +
                ", sender='" + sender + '\'' +
                ", target='" + target + '\'' +
                ", timeReceived=" + timeReceived +
                ", FileType='" + FileType + '\'' +
                ", sizeInMem=" + sizeInMem +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof messages)) return false;
        messages messages = (messages) o;
        return timeReceived == messages.timeReceived && sizeInMem == messages.sizeInMem && Objects.equals(textContent, messages.textContent) && Objects.equals(FileURI, messages.FileURI) && Objects.equals(sender, messages.sender) && Objects.equals(target, messages.target) && Objects.equals(FileType, messages.FileType) && Objects.equals(byteArray, messages.byteArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(textContent, FileURI, sender, target, FileType, byteArray, timeReceived, sizeInMem);
    }

    public UByteArray toByteArray(){//converts the message object to a BYteArray
        return null;
    }



}
