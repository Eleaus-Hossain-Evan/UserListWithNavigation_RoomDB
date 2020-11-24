package com.example.userlistwithnavigation_roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.userlistwithnavigation_roomdb.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("delete from user_table")
    suspend fun deleteAllUser()

    @Query("select * from user_table order by id asc")
    fun readAllData(): LiveData<List<User>>
}