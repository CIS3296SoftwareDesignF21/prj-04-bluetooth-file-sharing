package com.example.app.messages;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Objects;

public class messages {//message object will be sent over bT impl


    public String FilePath;//realy just a stand in for the name of anything recieved over bluetooth
    public String sender;//id of device sending the packet
    public String target;//id of device sending the packet
    public String FileType;
    public int timeReceived;
    public int sizeInMem; //reffering to the messages size in memory


    public messages(String FilePath, String sender, String target, int timeReceived, String FileType, int sizeInMem){
        this.FilePath = FilePath;
        this.sender = sender;
        this.target = target;
        this.FileType = FileType;
        this.timeReceived = timeReceived;
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
    public String toString() {//edit if any changes to the constructor is made
        return "messages{" +
                "FilePath='" + FilePath + '\'' +
                ", sender='" + sender + '\'' +
                ", target='" + target + '\'' +
                ", FileType='" + FileType + '\'' +
                ", timeRecievd=" + timeReceived +
                '}';
    }



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        messages messages = (messages) o;
        return timeReceived == messages.timeReceived && Objects.equals(FilePath, messages.FilePath) && Objects.equals(sender, messages.sender) && Objects.equals(target, messages.target) && Objects.equals(FileType, messages.FileType);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(FilePath, sender, target, FileType, timeReceived);
    }



    public File loadFile(){
        File cuurentItem = new File(this.FilePath);
        return null;
        
    }


    //next add a comparator so sorting with arrays is mad easy
    //with CComparator you can compare by different factors
    //comparable sets a default item to compare by and may be required by soring functions of other data types

}
