package com.skyblue.roomdatabase.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skyblue.roomdatabase.model.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: List<Contact>)

    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<Contact>
}