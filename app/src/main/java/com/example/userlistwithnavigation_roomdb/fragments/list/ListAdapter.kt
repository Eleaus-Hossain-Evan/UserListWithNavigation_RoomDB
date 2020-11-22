package com.example.userlistwithnavigation_roomdb.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userlistwithnavigation_roomdb.R
import com.example.userlistwithnavigation_roomdb.data.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewholder = UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
        return viewholder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.itemView.tvID.text = currentItem.id.toString()
        holder.itemView.tvFirstName.text = currentItem.firstName
        holder.itemView.tvLastName.text = currentItem.lasttName
        holder.itemView.tvAge.text = currentItem.age.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}