package com.skyblue.roomdatabase.repository

import com.skyblue.roomdatabase.model.Contact
import com.skyblue.roomdatabase.room.ContactDao


class ContactRepository(private val contactDao: ContactDao) {
    suspend fun insertContact(contact: List<Contact>) = contactDao.insertContact(contact)
    suspend fun getAllContacts() = contactDao.getAllContacts()

    suspend fun deleteAllContacts() = contactDao.deleteAllContacts()
}