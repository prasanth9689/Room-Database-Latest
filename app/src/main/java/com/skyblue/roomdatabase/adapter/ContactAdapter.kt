package com.skyblue.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skyblue.roomdatabase.R
import com.skyblue.roomdatabase.model.Contact

class ContactAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount() = contacts.size
}

class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.contact_item, parent, false)) {
    private var firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
    private var phoneNumberTextView: TextView = itemView.findViewById(R.id.phoneNumberTextView)


    fun bind(contact: Contact) {
        firstNameTextView.text = contact.firstName
        phoneNumberTextView.text = contact.phoneNumber
    }
}