package com.example.userlistwithnavigation_roomdb.data

import androidx.lifecycle.LiveData

class UserRepositiory(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

}