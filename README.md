## Overview 
This repo contains the Dissertation work of Sean Moylan and Shane Moran. 

## Details

| Project Details   |     |
| --- | --- |
| **Course** | BSc (Hons) in Software Development  |
| **Module** |  Applied Project and Minor Dissertation |
| **College** | [Galway-Mayo Institute of Technology](http://www.gmit.ie/) Galway |
| **Students** | Sean Moylan & Shane Moran |
| **Project Supervisor** | Martin Kenirons |
| **Module Supervisor** | John Healy |
| **Project Title** | Spot Finder Android Application |



## Technologies 

| Name                                                         | Use                                                   |
| ------------------------------------------------------------ | ----------------------------------------------------- |
| [Android Studio 3.6.1](https://developer.android.com/studio) | Front end android application development in java 8   |
| [Python3](https://www.python.org/downloads/)                 | Language used to program the Server                   |
| [Flask Server](https://flask.palletsprojects.com/en/1.1.x/)  | A micro web framework written in Python               |
| [MongoDB 4.2.2](https://www.mongodb.com/)                    | Database for Application                              |
| Terminal                                                     | Used for running the server and retrieving any errors |
| macOS Catalina - 10.15.4                                     | OS used for the development of the Project            |



## Run

Asumming you have all the technologies listed above installed on your device please follow the steps bellow to set up a working environment of this repository on your own machine.

### Android Studio

1. Clone this repo to your device by navigating to your devices terminal and running the following command `git clone <https://github.com/seanmoylan/Applied_Project>`
2. Open Android Studio and click "Open and existing Android Studio project" then navigate the the folder you cloned the repo to.
3. Once inside "Applied_Project"  you will see a folder named "Spot Finder App", select it and give android studio a moment to get everything set up.
4. Done

### Server

1. Clone this repo to your device by navigating to your devices terminal and running the following command `git clone <https://github.com/seanmoylan/Applied_Project>`

2. Open Terminal and navigate to the "Server" directory found in the "Applied_Project" directory

3. Once you are in the Server Directory run the following commands:

4. `export FLASK_APP=FlaskServer.py` this will tell flask what program to run

5. `export FLASK_ENV=development` to run in development mode

6. Finally `flask run` to start the flask server

7. You should now see

    \* Serving Flask app "FlaskServer.py" 

    \* Environment: development

    \* Debug mode: on

    \* Running on http://127.0.0.1:5000/ (Press CTRL+C to quit)

    \* Restarting with stat

    \* Debugger is active!

    \* Debugger PIN: 605-582-474

8. Navigate to a browser of your choice and enter the url above in the address window

9. Now the text "Server is running..." should be displayed

10. See screenshot below of server taking requests

    ![Flask Server](/Users/seanmoylan/Desktop/Screenshot 2020-05-09 at 17.26.03.png)

### Database

1. Please visit the site suited to your devise for a full breakdown of how to set up MongoDB
2. [Windows](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/)
3. [Mac](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/)
4. [Linux](https://docs.mongodb.com/manual/administration/install-on-linux/)
5. Once you have Mongo fully installed on your device, the FlaskServery.py will create an instance of a database when it is run for the first time.

## Testing





