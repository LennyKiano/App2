# App2
App test for InterIntel Kenya
The test is as follows Test the app should have 3 modules

Core
Should have a background service that can be started from a button click in either app1 or app2 below, when started, the service should make 5 seconds scheduled network requests to https://httpbin.org/get and Toast the origin from the response
app1
have an activity with a button that can be clicked to show a notification
Have a custom font that looks different from app2
app2
have inputs of saving users into the database
have a list view of displaying the users
have a custom view named TimeElement that displays the current date and time app1 should be compile-able to an apk named App1 app2 should be compile-able to an apk named App2 core should not be compile-able to an apk app1 and 2 should have different launcher icons app1 and 2 should have different launcher activities extending a base activity implemented in core

SOLUTION APP2

1.The Launcher for app2 is a splash screen that has a custom logo imageView and textView below it as a title of the app 
*The imageView and TextView has the same animation effect of fading in where the alpha is set to 0 and after 5 seconds
the aplha is set to 1, thus causing a fading in effect.
*After 6 seconds the LauncherActivity opens the MainActitity

2. The MainActivity has a simple yet effective UI that has a cardView that contains textInputLayouts and textFields that accpets only
a name and email, with 2 buttons: "View USers","Submit"
  *The textInputLayouts and textFields have a validating methods to ensure correct format of data
  *The database used for the application is a Mysqli database that is in a java class called 'DataBaseeHelper'
  *Inshort it calling java code from Kotlin
  *"Submit Button" after validating will run a method to enter the data into the database
  *To view the data the "View useres" button should be tapped that launches an intent to the 'UserActivity'
 
 3. For the Custom View, the TimeElement class extends the textView class that will be used to display the current date and time
  * Oncreate of the mainActivity will run the methos to get the current date and time but it is static since oncreate is only called when the 
  activity is seen by the user
 
 4.UserActvity has a ListView with a custom Adapter and view holder that inflates a custom view that has a name and email as textView
    *onCreate will load data from the DB store them in an arrayList that is passed to the customAdapter that will handle to population of the listView
    
 NOTE: incase of anyting missed out refer to the project code as I have done my best to comment what is going on and the logic
 NOTE: The app design not optimized for hdpi screen sizes as it is just a test and not a real world application
    
 To see the solution of App1 and Core check it out in my other REPO here : https://github.com/LennyKiano/App1
    
  //HAPPY CODDING
    
