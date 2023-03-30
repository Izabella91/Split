package com.example.myapplication

import android.R
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment.STYLE_NO_TITLE
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentAddContactDialogBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider



class AddContactDialogFragment : DialogFragment() {

    private var _binding: FragmentAddContactDialogBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactDialogBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if(it == null){
                getString(R.string.ok)
            }else{
                getString(R.string.cancel, it.message)
            }

            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            dismiss()
        })

        binding.buttonAdd.setOnClickListener{
            val fullName = binding.editTextFullName.text.toString().trim()
            val contactNumber = binding.editTextContact.text.toString().trim()

            if(fullName.isEmpty()){
                binding.editTextFullName.error = "This field is required"
                return@setOnClickListener
            }

            if(contactNumber.isEmpty()){
                binding.editTextContact.error = "This field is required"
                return@setOnClickListener
            }

            val contact = Contact()
            contact.fullName = fullName
            contact.contactNumber = contactNumber

            viewModel.addContact(contact)
        }
    }


}