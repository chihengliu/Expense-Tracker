# Expense Tracker
**Developed by** *Team CaptainJava*  
**Team Member** *Yuewei Yang, Chengzhang Ma, Chiheng Liu*
## Overview
This is source code for Android App Expense Tracker that has a server and a mysql database set up on a Duke OIT virtual machine. 
The code submitted by April 21th on Master branch is for Sprint 3 which has functions allowing users to:
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
All data displayed on this app is stored in MySQL database. 

## Installation

1. Dowload Android Studio at [here](https://developer.android.com/studio/index.html?gclid=CjwKEAiA0fnFBRC6g8rgmICvrw0SJADx1_zASntPtDFk4kuUeoPWy__8XLeW76pvfyuSTsSKh2FBHxoCJw7w_wcB). 
2. (Make sure the version of Android Studio is 2.3.1)

2. Change to an empty directory clone this repository, run:
```
git clone git@gitlab.oit.duke.edu:ECE651_S17_PROJECTS/CaptainJava.git
```

3. Open project named "CaptainJava" in the directory you just git cloned the code.

4. Update API to latest version and install all required packages as asked by Android Studio. 

5. Build and make the project to make sure everything is installed.

6. Run the app by "starting" button, an Android phone emulator needs to be installed. For preference we reconmend you to install **Nexus 5 API 25**.

7. If everything is ready, you can run the app on an emulator as you do on a phone. 

8. Or an Android system phone can be connected to the computer and app can be run on the connected phone.

![Slide01](/uploads/6cea84ff49421377d7c6c8ac3b447b3d/Slide01.jpg)
![Slide02](/uploads/eb62c3825baac590625ec1f5335166fc/Slide02.jpg)

## File structure
In file **app** there are 3 subfolders: 
<ul>
<li>*manifest* has an xml file for application configuration</li>
<li>*java* has object classes and activity classes</li>
<li>*res* has all activity layouts and some UI animations</li>
</ul>

## API Reference
### Log in
[http://152.3.52.123/login.php](http://152.3.52.123/login.php) 
### Register
[http://152.3.52.123/register.php](http://152.3.52.123/register.php)
### Get all personal spending records
[http://152.3.52.123/updateList.php](http://152.3.52.123/updateList.php)
### Add, remove and update personal spending
[http://152.3.52.123/addSpending.php](http://152.3.52.123/addSpending.php)<br/>
[http://152.3.52.123/updateSpending.php](http://152.3.52.123/updateSpending.php)<br/>
[http://152.3.52.123/deleteSpending.php](http://152.3.52.123/deleteSpending.php)
### Get event list
[http://152.3.52.123/updateEventList.php](http://152.3.52.123/updateEventList.php)
### Member management and add, update, remove event info
[http://152.3.52.123/addEventAndMember.php](http://152.3.52.123/addEventAndMember.php)<br/>
[http://152.3.52.123/updateEventAndMember.php](http://152.3.52.123/updateEventAndMember.php)<br/>
[http://152.3.52.123/deleteEvent.php](http://152.3.52.123/deleteEvent.php)
### Add, remove and update event spending
[http://152.3.52.123/downEventSpend.php](http://152.3.52.123/downEventSpend.php)<br/>
[http://152.3.52.123/addEventSpend.php](http://152.3.52.123/addEventSpend.php)<br/>
[http://152.3.52.123/updateEventSpend.php](http://152.3.52.123/updateEventSpend.php)<br/>
[http://152.3.52.123/deleteEventSpend.php](http://152.3.52.123/deleteEventSpend.php)

## Database access
A mysql database is installed on OIT issued debian VM.

To connect to VM, run:
```
ssh bitnami@colab-sbx-370.oit.duke.edu
```
in terminal.Password is CaptainJava.

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

# User Guide

## Login/Logout & Register
A new user to the app can register an account with just a username and a password. Return user should be able to login with saved username and password. Logout button will be available once a user is logged in.

![Slide03](/uploads/ffc8d50c4804e1dc0d92d2982751c3d5/Slide03.jpg)

## Personal Spending
In personal menu, the user can create a spending that contains information such as category, amount, description, and date. With an existing expense, the user can modify the information of that expense or delete that expense. The user can view all personal expenses in graphical fashion (bar/pie). 

![Slide04](/uploads/48a852869bdee788099db694f15fb1a3/Slide04.jpg)

The figure above is the flow chart of personal spending activity. Detailed graph of list view is shown below. In list view,  <img src="/uploads/2722e33b789552f30b98c7c12942b0eb/pback.png" width="20"> button should return to main menu; <img src="/uploads/8a50261217d0d19b603415487d14456e/padd.png" width="20"> button allows user to create a new spending; <img src="/uploads/0e0caab9a3433fffac2f4e11fc9e7ded/psort.png" width="100"> are search buttons that sort all expenses by date of creation; <img src="/uploads/e4c08ec1c114bf4ce4af395f603eba63/pcharts.png" width="100"> can demonstrate bar/pie charts of all spending. In creating page, category can be chosen in a drop-down bar. Amount and description can be typed and date can be selected in a calendar view. User can cancel or save new/edited spending, or can delete an existing spending. Click on an existing expense will allow user to change detail information of that expense.

![Slide06](/uploads/be8fd7e3bef949846d86cc2d1105fdb5/Slide06.jpg)!![Slide07](/uploads/47892c17781940617672172f0aa1b1ec/Slide07.jpg)

## Event Spending
In event menu, the user can create an event that contains information such as name of the event, description, and member. Within each event the user can apply on individual spending in the same way as personal spending. The user can create a spending that contains information such as category, amount, description, and date. With an existing expense, the user can modify the information of that expense or delete that expense. The user can view all personal expenses in graphical fashion (bar/pie).

![Slide05](/uploads/4cf93d14b26862600b196a9065c7459a/Slide05.jpg)

The figure above is the flow chart of personal spending activity. Detailed graph of list view is shown below. In event list view, <img src="/uploads/7574121dd8354fd1ac3214bc23dca80e/eback.png" width="20"> button should return to main menu; <img src="/uploads/8efe102bd707cf8e5de8bd33b02f2c1e/eadd.png" width="20"> button allows user to create a new event. In event creating page, event name and description can be typed and select members in a new window. User can cancel or save new event. Clicking on an existing event can lead the user to the event spending list, and instructions are the same as in personal spending list view. <img src="/uploads/7574121dd8354fd1ac3214bc23dca80e/eback.png" width="20"> button returns to event list and <img src="/uploads/8efe102bd707cf8e5de8bd33b02f2c1e/eadd.png" width="20"> create new spending. <img src="/uploads/71a47e09e1ce82a507ab13c1b4b85a04/esort.png" width="100"> are search buttons that sort all expenses by date of creation; <img src="/uploads/5feb72a06a027cb493daf63b2e9c772a/echart.png" width="100"> can demonstrate bar/pie charts of all spending. In creating page, besides category, amount, description, and date, member who spend this money can be selected in a drop-down bar. Click on an existing expense will allow user to change detail information of that expense.  button allows the user to edit information about the event and manage members within this event or delete the event. 

![Slide08](/uploads/e40ccecebc5fe1f81ee8161dbd3a95ce/Slide08.jpg)![Slide09](/uploads/594ab4e3fd75ba84e841ec0b7c93ba09/Slide09.jpg)![Slide10](/uploads/a319019e8c8683250ba3f8eb27346d28/Slide10.jpg)![Slide11](/uploads/5d00d71c4b12474d64214774b1da0607/Slide11.jpg)


