# Bluetooth File Sharing

Trello Board Link: https://trello.com/b/GyVu70Z6/bridgefy





## Project Abstract

The goal of this project will be to create an app(android or ios) that allows users to upload
specific file formats to mesh/ad-hoc networks. Users will then be able to connect to the same network
to download and view the files. Ad-hoc networks refer to networks constructed on the fly via peer to
peer connections, in this case all connections will be made with  Android's Bluetooth API. The app will 
consist of a storage system for content to be uploaded or downloaded, and a user interface capable of 
interacting with the storage system. The project will rely heavily on the Bluetooth protocol for the creation 
of networks and sending files and messages.


## Project Relevance

This project will likely include all the education goals. The app will make use a of GUI to
allow the user to perform various actions. Multi-threading and parallelism are important for any
application that uses networking to allow app functionality while waiting for responses over a network.
UML, TDD, object-oriented design, and debugging will all be necessary for a project of this scale and
complexity.

## Conceptual Design

**Definite Goals**
* Simple User interface
    *  Two screens:
       - one for making and sending messages/files
       - one for viewing received messages/files

* Simple Network interface
    - A function for sending to nearby devices
    - A function for listening for incoming messages and handling them


**Optional Goals**
- Packet forwarding
- Sending to specific users
- Message database
- Hash tables for packet sorting and storing
- More complex user interface

 
 
##  Vision statement
FOR teachers, people without reliable internet, communicator, and more WHO need a way to quickly and 
reliably transfer files to near-by recipients, THE FileShAIR mobile app is a bluetooth driven app
THAT sets up a file sharing network from one phone to multiple others via bluetooth

UNLIKE Android Nearby Share, users can choose to listen in so that the files arent available to 
everyone. This leads to a pseudo-community-privacy file share channel. OUR app allows for simple file
transfer between expecting parties who need the data quickly.

##  Feature list / Use cases
##  Minimal project


## Required Resources

**This project uses:**
* Java
* Kotlin

**To Build**
This project must be built using gradle and the Android SDK >6.0. To built all that is needed is to execute the 
gradle build scripts, this is done very easily using either intelliJ or Android Studio. Running the application
requires an Android device emulator or a physical device. 
