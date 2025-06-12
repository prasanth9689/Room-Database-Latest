package com.skyblue.roomdatabase


class ContactRepository(private val contactDao: ContactDao) {
    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)
    suspend fun getAllContacts() = contactDao.getAllContacts()
}