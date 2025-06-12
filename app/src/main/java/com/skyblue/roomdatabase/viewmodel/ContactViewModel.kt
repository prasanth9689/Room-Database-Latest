package com.skyblue.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyblue.roomdatabase.model.Contact
import com.skyblue.roomdatabase.repository.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    fun insertContact(contact: List<Contact>) = viewModelScope.launch {
        repository.insertContact(contact)
    }

    fun getAllContacts() = viewModelScope.launch {
        _contacts.value = repository.getAllContacts()
    }

    fun deleteAllContacts() {
        viewModelScope.launch {
            repository.deleteAllContacts()
        }
    }

}