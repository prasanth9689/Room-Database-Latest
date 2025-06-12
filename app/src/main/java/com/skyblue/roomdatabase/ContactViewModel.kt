package com.skyblue.roomdatabase

// ContactViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {
    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    fun insertContact(contact: Contact) = viewModelScope.launch {
        repository.insertContact(contact)
    }

    fun getAllContacts() = viewModelScope.launch {
        _contacts.value = repository.getAllContacts()
    }
}