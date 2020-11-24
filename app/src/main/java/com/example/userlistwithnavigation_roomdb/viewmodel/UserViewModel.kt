package com.example.userlistwithnavigation_roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.userlistwithnavigation_roomdb.data.UserDatabase
import com.example.userlistwithnavigation_roomdb.repository.UserRepositiory
import com.example.userlistwithnavigation_roomdb.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>

    private val repositiory: UserRepositiory

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repositiory = UserRepositiory(userDao)

        readAllData = repositiory.readAllData
    }

    fun addUser(user: User)= viewModelScope.launch(Dispatchers.IO) {
        repositiory.addUser(user)
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repositiory.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repositiory.deleteUser(user)
        }
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repositiory.deleteAllUser()
        }
    }

}