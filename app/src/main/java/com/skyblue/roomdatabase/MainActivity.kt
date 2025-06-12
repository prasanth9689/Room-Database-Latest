package com.skyblue.roomdatabase

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.skyblue.roomdatabase.adapter.ContactAdapter
import com.skyblue.roomdatabase.databinding.ActivityMainBinding
import com.skyblue.roomdatabase.model.Contact
import com.skyblue.roomdatabase.repository.ContactRepository
import com.skyblue.roomdatabase.room.AppDatabase
import com.skyblue.roomdatabase.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contactDao = AppDatabase.getDatabase(this).contactDao()
        val repository = ContactRepository(contactDao)
        viewModel = ViewModelProvider(this, ContactViewModelFactory(repository))[ContactViewModel::class.java]

        viewModel.deleteAllContacts()
        onClick()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            viewModel.contacts.collectLatest { contacts ->
                adapter = ContactAdapter(contacts)
                binding.recyclerView.adapter = adapter
            }
        }
        viewModel.getAllContacts()
    }

    private fun onClick() {
        binding.addContactsMultiple.setOnClickListener {
            val contactList = listOf(
                Contact(firstName = "Prasanth 66", phoneNumber = "1234567890"),
                Contact(firstName = "Akila 2", phoneNumber = "2345678901"),
            )
            viewModel.insertContact(contactList)
            viewModel.getAllContacts()
        }

        binding.addSingle.setOnClickListener {

            val firstName = binding.inputName.text.toString()
            val phoneNumber = binding.inputPhone.text.toString()
            val contact = Contact(firstName = firstName, phoneNumber = phoneNumber)
            viewModel.insertContact(listOf(contact))
            viewModel.getAllContacts()
        }
    }
}

class ContactViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}