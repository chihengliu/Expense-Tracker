# Expense Tracker
This is source code for Android App Expense Tracker that has a server and a mysql database set up on a Duke OIT virtual machine. 
The code submitted by April 18th on Master branch is for Sprint 3 which has functions allowing users to:
<ul>
<li>View list of individual spendings and event names</li>
<li>Create a new individual spending</li>
<li>Edit/remove an existing individual spending</li>
<li>Create a new event</li>
<li>Edit/remove an existing event</li>
<li>Manage members in an event</li>
<li>Rearrange list according to time of creation</li>
<li>View spending records in pie chart and bar chart according to members or categories</li>
</ul>
All data displayed on this app is stored in mysql database. 

# Getting Started
## Android Studio

Change to an empty directory clone this repository, run:
```
git clone git@gitlab.oit.duke.edu:ECE651_S17_PROJECTS/CaptainJava.git
```
1. Dowload Android Studio at [here](https://developer.android.com/studio/index.html?gclid=CjwKEAiA0fnFBRC6g8rgmICvrw0SJADx1_zASntPtDFk4kuUeoPWy__8XLeW76pvfyuSTsSKh2FBHxoCJw7w_wcB). 

Open Android Studio 

Open project named "CaptainJava" in the directory you just git cloned the code.

Update API to latest version and install all required packages as asked by Android Studio. 

Build and make the project to make sure everything is installed.

Run the app by "starting" button, an Android phone emulator needs to be installed. For preference we reconmend you to install **Nexus 5 API 25**.

If everything is ready, you can run the app on an emulator as you do on a phone. 

## File structure
In file **app** there are 3 subfolders: 
<ul>
<li>*manifest* has an xml file for application configuration</li>
<li>*java* has object classes and activity classes</li>
<li>*res* has all activity layouts and some UI animations</li>
</ul>

## Database access
A mysql database is installed on OIT issued debian VM.

To connect to VM, run:
```
ssh bitnami@colab-sbx-370.oit.duke.edu
```
in terminal.Password is bRumerern0.

To connect to database, run:
```
mysql -u visitor -p
```
in VM. Password is visitors.

To change to database, run:
```
use ExpenseTracker
```

To view data in *Personal* table, run:
```
select * from Personal;
```