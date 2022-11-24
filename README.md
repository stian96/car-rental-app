# Group_Project

This project was created for a Software Engineering and Testing course at Østfold University College. The application uses [Gradle](https://gradle.org/) v7.2 to build and download external dependencies. Java is the main programming language used, and [JavaFX](https://openjfx.io/) v7.0.1 is used for the GUI. Persistent storage is saved locally using a [SQLite](https://www.sqlite.org/index.html) database. The testing framework used is [JUnit](https://junit.org/) v5.9.0, as well as [Mockito](https://site.mockito.org/) v4.8.0. The [Nominatim](https://nominatim.org/) API was used to query [OpenStreetMaps](https://www.openstreetmap.org/) and retrieve additional geolocation data upon input from the user.

The following instructions are for **Windows 10** users (the application has been tested on a Mac OS, and has run as desired, but the following code syntax may have to be adjusted) 

**_Step One_**

Downloading the project (using [git](https://github.com/git-guides/install-git))
- Press the windows key and type `cmd` to open the Command Prompt window.
- Navigate to the desired directory and type `git clone https://github.com/stian96/Group_Project.git`.

Downloading the project (from GitHub)
- Click on the green *'Code<>'* button to access a dropdown menu, and click on *'Download ZIP'*.
- Unpack the .zip file to the desired directory.

The application requires **Java 17** to run and can be downloaded [here](https://www.oracle.com/java/technologies/downloads/#jdk17-windows). 

**_Step Two_**

Building the application
- Navigate to the project using the Command Prompt and open the *java_project* folder.
- Make sure that your **JAVA_HOME** path is set to wherever you downloaded JDK 17.x
  - To check that the correct path is set in Windows you can type `echo %JAVA_HOME%` in the Command Prompt.
    - You can manually change the default **JAVA_HOME** path in System > Advanced System Settings > Advanced > Environement Variables.
    - To temporarily change the path you can enter `set %JAVA_HOME%="<path to JDK 17>"` in cmd.
- Enter `gradlew wrapper` in cmd (If you don't already have Gradle installed then it may take a while to download Gradle v7.2.
- Enter `gradlew build -x test` in cmd to build the project and download external libraries.

**_Step Three_**

Launching the application
- Enter `gradlew run` in cmd to launch the application GUI.
  - The GUI has persistent storage and data entered during the running of the application (for example, creation of a user account, registration of a vehicle, or reservation of a booking) will be loaded in subsequent uses.
  
Running the tests
- Enter `gradlew test --tests "no.hiof.groupproject.*` to start running the tests

**_Alternative using IntelliJ_**

Opening the project in IntelliJ is also a valid option, especially for more detailed feedback of test results. Simply open up the project on the *'java_project'* folder and click on *'Build'* on the top toolbar, and select *'Build Project'* from the dropdown menu. 
- The GUI can be run directly from *'java_project/src/main/java/no/hiof/groupproject/gui/Main.java'* or indirectly from *'java_project/src/main/java/no/hiof/groupproject/Launcher.java'*.

- Additionally, the tests can be run from *'java_project/src/test/java/no/hiof/groupproject'*.


