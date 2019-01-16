package com.leonkianoapps.leonk.app2

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {





    private val userInfoFromDB : ArrayList<UserInfo> = ArrayList()  //To store user information from the DB



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        //setting up toolbar
        setSupportActionBar(users_toolBar)
        supportActionBar!!.title = "App2 Users"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Setting up Floating action Button
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {

            onBackPressed()  //go to previous activity
        }


        //get data from DB
        getDataFromDB()

        //Setting up listView

        usersListView.adapter = ListViewCustomAdapter(applicationContext,userInfoFromDB)
        usersListView.setOnItemClickListener { adapterView, view, position, l ->


           val nameTapped = userInfoFromDB[position].name   //getting name from the arrayList based on the position

            showSnack("$nameTapped is tapped")
        }

        //checking to see if there is data stored if not displaying message

        if(userInfoFromDB.isEmpty()){  infoTextView.visibility = View.VISIBLE  }else { infoTextView.visibility = View.INVISIBLE}


    }

    private fun getDataFromDB() {

        val mDataBaseHelper  = DataBaseHelper(applicationContext)


        val allData : Cursor = mDataBaseHelper.data


        if (allData!=null){

                        if (allData.moveToFirst()){

                            do{
//

                                val nameFromDB = allData.getString(1)  //getting name
                                val emailFromDb = allData.getString(2) //getting email

                                val enterUser = UserInfo(nameFromDB,emailFromDb)

                                userInfoFromDB.add(enterUser)   //adding it to the arrayList

                            }while (allData.moveToNext())

                        }

                    }

    }


    private fun showSnack(info : String){

        val snack = Snackbar.make(userCoordinatorLayout,info, Snackbar.LENGTH_LONG)
            .setAction("OKAY", View.OnClickListener {
            })
        snack.show()


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        if (id == android.R.id.home) {

            onBackPressed()  //go back to the previous activity

            return true
        }

        return super.onOptionsItemSelected(item)
    }


}
