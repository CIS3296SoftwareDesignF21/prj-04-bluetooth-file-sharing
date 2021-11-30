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

This project will touch on many of the education goals for this course. The app will make use a of GUI to allow the user to perform various actions. It will utilize multi-threading and parallelism to allow the app to function while performing time consuming network actions. UML, TDD, object-oriented design, and debugging will all be utilized as well given the scale of what we are trying to accomplish.

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
FOR people without reliable internet/cell service and others WHO need a way to quickly and 
reliably transfer files to near-by recipients, THE file sharing mobile app is a Bluetooth driven app
THAT sets up a file sharing network from one phone to others via bluetooth.

UNLIKE other Bluetooth file transfering solutions, users can route their files through other devices to increase the physical range of file sharing capabilities. This leads to a pseudo-community-privacy file sharing channel. OUR app allows for reliable file transfer between expecting parties who may not have other file transferring options.

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

This app is an alternative to other file transfering solutions like mobile data, WiFi, and physical devices. See the personas for more detailed use cases of this app.

# Personas

## Persona 1 - Carl: an average, not so technically adept user - shares files without Wifi or cellular

Carl is a simple man who lives in a remote village without cellular or wireless internet access. He and everyone he knows uses the Bluetooth File Transfer app religiously, as it's their only way to exchange files in the absence of other forms of telecommunication. Carl is a coal miner by trade, and his brother Tom is a 
construction worker. Carl joined the workforce out of high school. He has to go to a nearby city to visit Tom and use the Wifi to connect with the outside world.

Tom recently bought Carl an Android phone for his birthday. However, Carl doesn't really know how to use it.
He has a bare minimum understanding of technology; he knows how to take a picture and open an app but that's about it. 
Tom recently heard of the Bluetooth File Transfer app and downloads it to Carl's phone. He explains that the Bluetooth File Transfer app does not need Wifi or cellular data to send messages.
By using the Bluetooth File Transfer app, Carl and Tom can exchange messages over large distances. Fortunately, there are enough users between him and his brother to bridge the gap between them via multiple hops of bluetooth. Carl now uses the Bluetooth File Transfer app to send 
pictures of his kids to his brother.  

## Persona 2 - Tony: Introverted Student - mass communication at once

Tony is a really shy student who knows how to use his phone and is on social media. Tony is in the 11th grade, and is fairly good with technology.
He is on many Discord servers, and keeps up with the latest tech news. He is an avid gamer and tends to maintain his contacts with people virtually. 

Tony wants to throw a party to become more popular, but he is afraid to invite his classmates face to face. Tony uses the Bluetooth File Transfer app
at school to send invitations to everyone there who also has the app. The invitation includes pictures and a message
with details about the party for everyone to see. Everyone gets the invitation and thinks that Tony is super cool for using
Bluetooth to invite them. The party was a massive success. Tony became one of the most popular kids in school, and dropped out to pursue a lucrative career in rap.

## Persona 3 - Peter: Setting Up A New Phone - bulk transfer

Peter is definitely not an expert with technology, but he knows his way around his phone. He works as a construction manager of his own company, 
and has a bachelor's in finance. He chooses to buy his music off the internet rather than pay for subscription services. 
He gets a new android phone and would like to transfer his music library stored in a folder on his current android phone. 
Peter has also used his phone as his general purpose storage device over the years and would like to tranfer in bulk, files of different types. 
He realized that using the Bluetooth File Transfer app he can select all his old files and send them over to his new phone.

Why this option is particulary of interest to Peter is that his current phone is not a newer one that supports quick 
setup processes over mobile date or WiFi, so his options are limited. Thanks to our Bluetooth File Tranfer App, there is
an easy solution for that. Rather than sending files by first going into the Gallery, sending over bluetooth, then 
going into his music library and then doing the same before finaly repeating with his videos or any other files he will
like to send over to his new phone, Peter can use the app's ability to send multiple files of different types all at once
making his setup process hassle free.

## Persona 4 - Jennifer: An old-fashioned teacher - hold files for later use

Jennifer is a 26 year old high school English teacher. She completed her bachelors degrees in teaching and English
 at Temple University in 2019. She knows very little about technology outside of basic laptop and word processor usage. 
She spends most of her time rigorously working on lesson plans and for her students and raising her new born child. 
She doesnt have time to keep up to date with the newest technology or communication trends. 

Jennifer's curiculum involve various handouts, worksheets, and quizes. She has never been one for accepting change in her line of
work. However, with COVID-19 still prevalent, she needs a paperless, reliable, and quick way to get handouts to her students in class on demand.
As much as she was opposed to integrating online solutions into her work, Jennifer uses her limited technology skills to share her materials with her class. She needs her students to be able to retain the wokrsheets so they can send it back. The Bluetooth File Transfer app allows for this by storing her worksheets so the students can access them later on, or send them back to her when they are completed. 

## Persona 5 - Emma: A genius hacker - untraceable communication

Emma is a genius hacker who knows the ins and outs of every computer system on the planet. With a Masters in computer science at UPenn and over 20 years of industry experience, she's the best of the best. Emma was facing a large amount of debt after an over-confident night at the casino. She had to find a way to get money  fast, so she did some scouting and found an up-and-coming company with billions of dollars worth of data. She found a major security problem in their system, and wasted no time writing an exploit to steal their data.

After pulling off this multi-billion dollar digital heist, Emma had to meticulously cover her tracks. Determined to evade detection by the NSA and other government agencies, Emma turned to the Bluetooth File Transfer app to securely exfiltrate the stolen data to her buyers. The Bluetooth File Transfer app is the ideal solution to her problems, as it avoids common channels of communication that are more likely to be monitored by the people trying to catch her in the act. With no one knowing who she is or where the communication is going to, she can easily avoid detection. She now lives in a multi-million dollar mansion without a care in the world.

## Required Resources

**This project uses:**
* Java
* Kotlin

**To Build**
This project must be built using gradle and the Android SDK >6.0. To build, all that is needed is to execute the 
gradle build scripts. This is done very easily using either intelliJ or Android Studio. Running the application
requires an Android device emulator or a physical device. 


