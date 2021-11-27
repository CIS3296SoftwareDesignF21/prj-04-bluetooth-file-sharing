package com.example.app.messages;

import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import kotlin.UByteArray;

import java.io.File;
import java.util.Objects;

public class messages {//message object will be sent over bT impl


    public String FilePath;//realy just a stand in for the name of anything recieved over bluetooth
    public Uri FileURI;
    public String sender;//id of device sending the packet
    public String target;//id of device sending the packet
    public String FileType;
    public UByteArray byteArray;
    public int timeReceived;
    public int sizeInMem; //reffering to the messages size in memory


    public messages(String FilePath , UByteArray byteArray, String sender, String target, int timeReceived, String FileType, int sizeInMem){//contructor that has the file path
        this.FilePath = FilePath;
        this.sender = sender;
        this.target = target;
        this.FileType = FileType;
        this.timeReceived = timeReceived;
        this.sizeInMem = sizeInMem;
        this.byteArray = byteArray;
    }

    public messages(Uri FileURI, UByteArray byteArray, String sender, String target, int timeReceived, String FileType, int sizeInMem){// constructor with URI that might be prefered by android
        this.FileURI = FileURI;
        this.sender = sender;
        this.target = target;
        this.FileType = FileType;
        this.timeReceived = timeReceived;
        this.sizeInMem = sizeInMem;
        this.byteArray = byteArray;
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
                "FilePath='" + FilePath + '\'' +
                ", FileURI=" + FileURI +
                ", sender='" + sender + '\'' +
                ", target='" + target + '\'' +
                ", FileType='" + FileType + '\'' +
                ", byteArray=" + byteArray +
                ", timeReceived=" + timeReceived +
                ", sizeInMem=" + sizeInMem +
                '}';
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof messages)) return false;
        messages messages = (messages) o;
        return timeReceived == messages.timeReceived && sizeInMem == messages.sizeInMem && Objects.equals(FilePath, messages.FilePath) && Objects.equals(FileURI, messages.FileURI) && Objects.equals(sender, messages.sender) && Objects.equals(target, messages.target) && Objects.equals(FileType, messages.FileType) && Objects.equals(byteArray, messages.byteArray);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(FilePath, FileURI, sender, target, FileType, byteArray, timeReceived, sizeInMem);
    }

    public File loadFile(){
        File cuurentItem = new File(this.FilePath);//instantiate a file using the path of the message
        return null;
    }

    public UByteArray toByteArray(){//converts the message object to a BYteArray
        return null;
    }


    //next add a comparator so sorting with arrays is mad easy
    //with CComparator you can compare by different factors
    //comparable sets a default item to compare by and may be required by soring functions of other data types

}
