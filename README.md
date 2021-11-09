# Bluetooth File Sharing

Trello Board Link: https://trello.com/b/GyVu70Z6/bridgefy

## Project Abstract

The goal of this project will be to create an Android app that allows users to upload
specific file formats to mesh/ad-hoc networks. Users will then be able to connect to the network via bluetooth
to download and view the files. Ad-hoc networks refer to networks constructed on the fly via peer to
peer connections, in this case all connections will be made with Android's Bluetooth API. The app will 
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
reliably transfer files to near-by recipients, THE Fileshare mobile app is a bluetooth driven app
THAT sets up a file sharing network from one phone to others via bluetooth.

UNLIKE Android Nearby Share, users can choose to listen in so that the files arent available to 
everyone. This leads to a pseudo-community-privacy file share channel. OUR app allows for simple file
transfer between expecting parties who need the data quickly.

##  Feature list
* Two screen UI
   - Message sending screen
   - Message receiving screen
* Bluetooth send functionality
* Bluetooth receive functionality
* Bluetooth forwarding functionality
* Sending to specific users
* Message database
* Possibly more if we end up having extra time

## Use Case

This app is alternative to other file transfering solutions like mobile data, WiFi, and physical devices. See the personas for more detailed use cases of this app.

## Required Resources

**This project uses:**
* Java
* Kotlin

**To Build**
This project must be built using gradle and the Android SDK >6.0. To built all that is needed is to execute the 
gradle build scripts, this is done very easily using either intelliJ or Android Studio. Running the application
requires an Android device emulator or a physical device. 


# Personas

## Persona 1 - Carl: an average, not so technically adept user

Carl is a simple man who lives in a remote village without access to cellular or wireless internet access. He and everyone he knows uses bridgefy religiously, 
as it's their only way to transfer files with the absence of other forms of telecommunication. Carl is a coal miner by trade, and his brother Tom is a 
construction worker. He has an Android phone but doesn't really know how to use it. He has a bare minimum understanding of technology; he knows how to take a 
picture and open an app but that's about it. Carl wants to use Bridgefy to send pictures of his kids to his brother who lives a few miles away. Fortunately, 
there are enough users between him and his brother to bridge the gap between them via multiple hops of bluetooth. 
