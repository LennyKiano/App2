package com.leonkianoapps.leonk.app2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

class ListViewCustomAdapter(var context : Context,var infoArrayList : ArrayList<UserInfo> ) : BaseAdapter() {


    private class ViewHolder(row: View?){

        var userName : TextView
        var userEmail: TextView


            init {

                this.userName = row?. findViewById(R.id.item_name) as TextView
                this.userEmail = row?. findViewById(R.id.item_email) as TextView
            }

    }

    override fun getView(postion: Int, convertView: View?, parent: ViewGroup?): View {

        var view : View?
        var viewHolder :ViewHolder

        if (convertView == null){

            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_user_info,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder

        } else{

            view = convertView
            viewHolder =view.tag as ViewHolder

        }

        var userIfo : UserInfo = getItem(postion) as UserInfo

        viewHolder.userName.text = userIfo.name
        viewHolder.userEmail.text = userIfo.email


        return  view as View

    }

    override fun getItem(postion: Int): Any {

        return infoArrayList[postion]


    }

    override fun getItemId(postion: Int): Long {

        return  postion.toLong()
    }

    override fun getCount(): Int {

        return  infoArrayList.count()
    }
}