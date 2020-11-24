package com.example.userlistwithnavigation_roomdb.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.userlistwithnavigation_roomdb.R
import com.example.userlistwithnavigation_roomdb.model.User
import com.example.userlistwithnavigation_roomdb.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnAdd.setOnClickListener(View.OnClickListener {
            insertDataToDatabase()
        })

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = etFirstName.text.toString()
        val lasttName = etLastName.text.toString()
        val age = etAge.text

        if(inputChack(firstName,lasttName,age)){
            //Creat User object
            val user = User(0, firstName,lasttName,Integer.parseInt(age.toString()))
            //Add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill-up all fields...", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChack(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}