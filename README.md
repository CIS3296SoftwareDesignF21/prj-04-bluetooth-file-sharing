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

## Persona 2 - Tony: Introverted Student

Tony is a really shy student who knows how to use his phone and is on social media. Tony wants to have party 
to become more popular, but he is afriad to invite his classmates face to face. So Tony uses the bluetooth sharing app
at school to send invitations to everyone there who also has the app. The invitation includes pictures and a message
with details about the party for everyone to see. Everyone gets the invitation and thinks that Tony is so cool for using
bluetooth to invite them that they all come. Tony becomes popular and gains confidence.

## Persona 3 - Peter: Picky Music Listener

Peter is definitely not an expert with technology, but he knows his way around his phone. He chooses to buy his music
off the internet rather than pay for subscription services. He gets a new android phone and will like to transfer his 
music library stored in a folder on his current android phone. He realized that using the Bridgefly app he can select
all his old files and send them over to his new phone.

## Persona 4 - Jennifer: An old-fashioned teacher who is too busy to stay on top of technology trends

Jennifer is a 33 year old English high school teacher. She completed up through her bachelors in teaching and english while at Temple University in 2019. She knows very little about technology
outside of laptop and word processor usage. She spends most of her time rigorously working on lesson plans and for her students and raising her new born child. She doesnt have time to keep
up to date with the newest technology or communication trends. Jennifer's curiculum involve various handouts, worksheets, and quizes.She has never been one for accepting change in her line of
work. However, with COVID-19 still prevalent, she needs a paperless, reliable, and quick way to get handouts to her students in class on demand. As much as she was opposed to integrating online
solutions into her line of work, Jennifer uses her limited technology skills to share her material with her class.

## Persona 5 - Emma: A genius hacker 

Emma is a genius hacker who knows the ins and outs of every computer system on the planet. With a Masters in computer science and over 20 years of industry experience, she's the best of the best. After pulling off a multi-billion dollar digital heist, Emma has to meticulously cover her tracks. Determined to evade detection by the NSA and other government agencies, Emma turns to bridgefy to securely exfiltrate the stolen data to her accomplices. Bridgefy is the ideal solution to her problems, as it avoids common channels of communication that are more likely to be monitored by the people trying to catch her in the act.  

## Persona 6 - Luis: Aspiring Artist

Luis is a 21 year old living in the city. He's quite adept with technology.
He won't ever be found without her android phone or computer. Often, in his spare time, 
he is making music on her computer and recording vocals on his phone.
When he finishes making songs he often sends them to his friends and family as a 
trial run using Bridgefy. Bridgefy allows him to easily market himself by sending him 
songs to everyone he knows. It is the key to making him a star.
