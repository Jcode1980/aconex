
Working with Aconex Excavation in Intellij 2017.3.1
===================================================

prerequisites
--------------
The following items should be installed in your system:
- Maven 3
- IntelliJ
- Java 9

Running The Aconex Excavation Application
--------------

**Inside Intellij**
- Open project into Intellij
    *Running All Tests*
    - Right click on src/main/java/com/aconex/excavation/Application.java
    - Click on "Run All Tests"

    *Running main App using default map (src/main/resources/SiteMap.txt)
    - Right click on src/main/java/com/aconex/excavation/Application.java
    - Click on "Run Application"

    *Running main App using custom Maps File*
    - Right click the aconex project folder
    - Go to Run -> Edit Configurations
    - On the left pane Create New Application Configuration
    - Select Application.java for the main class for the new Application Configuration
    - Under "Program Arguments" type in [SiteMap_FilePath]
    - Hit "Apply"
    - Right click on src/main/java/com/aconex/excavation/Application.java
    - Click Run on your newly created Run Application Configuration

    *Running exavation app with default set of instructions
    - Run through the same set of instructions as above to create a new
        RunApplication Configurtion but instead of specifing just the
        [SiteMap_FilePath] , you can also specify a default instructions
        file path.

**Terminal Command Line ** (Must have Maven installed for your terminal)
   *Running main App using default input commands File*
       mvn exec:java -Dexec.mainClass="com.aconex.excavation.Application"

   *Running main App using custom map*
       mvn exec:java -Dexec.mainClass="com.aconex.excavation.Application" -Dexec.args=[SITE_MAP_FILEPATH]
        e.g. mvn exec:java -Dexec.mainClass="com.aconex.excavation.Application" -Dexec.args="/PersonalProjects/aconex/src/main/resources/SiteMap.txt"

   *Running main App using custom map and default set of instructions*
          mvn exec:java -Dexec.mainClass="com.aconex.Appplication -Dexec.args=[SITE_MAPT_FILEPATH] [INSTRUCTIONS FILEPATH]
           e.g. mvn exec:java -Dexec.mainClass="com.aconex.excavation.Application"
            -Dexec.args="/PersonalProjects/aconex/src/main/resources/SiteMap.txt /PersonalProjects/aconex/src/main/resources/test/TestInstructionsFile.txt
"


Assumptions
===========
    -Valid commands with correct formatting is specified within the problems.md are as follows:
        l - left
        r - right
        a <n> - advance <n>
        q - quit

    -Commands are not case sensitive
    -As per requirement, the bulldozer at the start of the simulation
     will be situated at Northern edge of the site, immediately to the
     West of the site, and facing East.
    -Non valid or incorrectly formatted commands will be ignored and
     an error message will be printed to System.out.


Project Discussions
===================
    I choose to work on this task cause i found it to be interesting
    and would if felt like it would be a good challenge.

    Whilst reading the task, i saw that the requirements can be split
    into the job of clearing of the site using the bulldozer and then
    the invoicing for that job. I have therefore created two services,
    an Excavation Service and a Finance Service.




