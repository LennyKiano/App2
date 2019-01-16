package com.leonkianoapps.leonk.app2

import android.content.Intent
import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Calendar
import java.util.*


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setting up tool bar
        setSupportActionBar(main_toolBar)


        //Setting up text input layouts
        name_TextInputLayout.isCounterEnabled = true
        name_TextInputLayout.counterMaxLength = 20

        email_TextInputLayout.isCounterEnabled = true
        email_TextInputLayout.counterMaxLength = 50

        //setting up buttons
        submitButton.setOnClickListener { submitUser() }
        viewUsersButton.setOnClickListener { viewApp2Users() }


        //CustomView textView

        val customText : com.leonkianoapps.leonk.app2.TimeElement = findViewById(R.id.timeElementView)

        val currentTime = Calendar.getInstance().time.toString()   //getting current time


        customText.text = currentTime




    }

    private fun viewApp2Users() {

        val openActivityIntent = Intent(this@MainActivity,UsersActivity::class.java)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(openActivityIntent)

    }

    private fun submitUser() {

        //checking validity

        if (TextUtils.isEmpty(name_TextField.text.toString())) {    //if name is empty show error message

            name_TextInputLayout.error = "Please Enter a Name"

            return //to stop the method here

        }

        if (TextUtils.isEmpty(email_TextField.text.toString())) {   //if email is empty show error message


            email_TextInputLayout.error = "Please Enter a Email"


            return //to stop the method here

        }


        //check valid email

        if(!(Patterns.EMAIL_ADDRESS.matcher(email_TextField.text.toString()).matches())){    //if email is not correct format show error message

            email_TextInputLayout.error = "Please Enter a valid Email Address"


            return //to stop the method here


        }


        //Here information is valid/Good insert data to DB

        val name = name_TextField.text.toString()
        val email = email_TextField.text.toString()

        insertDataToDB(name,email)


        name_TextInputLayout.isErrorEnabled = false
        email_TextInputLayout.isErrorEnabled = false







    }

    private fun showSnack(messageInfo:String){

        val snack = Snackbar.make(coordinatorLayout,messageInfo,Snackbar.LENGTH_LONG)
            .setAction("OKAY", View.OnClickListener {
            })
        snack.show()

    }


    private fun insertDataToDB(name:String, email: String){

        val mDataBaseHelper  = DataBaseHelper(applicationContext)


        //first check if the name and email are already in the DB

        val dataName : Cursor = mDataBaseHelper.getUserName(name)
        val dataEmail : Cursor = mDataBaseHelper.getUserEmail(email)

        var returnedName  = ""
        var returnedEmail  = ""

        //checking if name is already in the DB

        if(dataName != null){

            if (dataName.moveToFirst()){

                do {

                    returnedName = dataName.getString(1).toString()   //getting the name from the DB, the number indicates the column number

                }while ( dataName.moveToNext())

            }

            if (returnedName.isNotEmpty()){  //means name is already in the DB

                showSnack("$returnedName is already used in the Database")

                return // to stop the method here

            }

        }

        //checking if email is already in the DB

        if(dataEmail != null){

            if (dataEmail.moveToFirst()){

                do {

                    returnedEmail = dataEmail.getString(2).toString()  //getting the email,the number,indicates the column number

                }while ( dataName.moveToNext())

            }

            if (returnedEmail.isNotEmpty()){  //means name is already in the DB

                showSnack("$returnedEmail is already used/ in the Database")

                return // to stop the method here

            }

        }



        //From here everything is good



        val insertData : Boolean = mDataBaseHelper.addData(name,email)      //calling java from kotlin

        if (insertData) {

            //Data was entered Successfully


            showSnack("$name was entered Successfully into the database" )

            //log to see if info was stored in DB correctly

            val allData : Cursor = mDataBaseHelper.data

            if (allData!=null){

                if (allData.moveToFirst()){

                    do{

                        val name = allData.getString(1)
                        val email =allData.getString(2)

                        Log.i("NAME and EMAIL from DB ","$name $email")

                    }while (allData.moveToNext())

                }

            }

        }//end of if insert Data

    }//end of function i






}
