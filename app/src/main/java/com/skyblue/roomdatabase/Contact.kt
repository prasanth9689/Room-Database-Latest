package com.skyblue.roomdatabase

// Contact.kt
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "contacts")
//data class Contact(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val firstName: String,
//    val phoneNumber: String
//)

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val phoneNumber: String
)
