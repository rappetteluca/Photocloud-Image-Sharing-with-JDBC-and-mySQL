#  Photocloud Image Sharing with JDBC and mySQL
## CS364

## Author(s):

Lucas Rappette

## Date:

3/16/18


## Description:

- A program that implements a GUI and a JDBC backend to access a mySQL database full of images posted by users.
Users are able to post their own images, view other photographers such as themselves, and purchase other images.
	- There are mySQL database scripts included for the creation and deletion of the database.
	- There is also a table layout file. This file is called _Photocloud Table Layout.pdf_.
	- This program requires that mySQL server be installed on your computer.

## How to build the software

Add this project to any Java IDE, it will automatically compile.
If this does not work execute the command below on the command line to build the project.

```
javac -d bin -sourcepath src src/*
```


## How to use the software

Create the database using the Create Database script and start the mySQL server.
Execute the command below on a command line in the directory, or run from the 
IDE with runtime arguments.

```
java -cp bin; GUI
```


## How the software was tested

Testing was completed by a team of people performing actions on the server. An in class
demonstration was also performed.


## Known bugs and problem areas

None.