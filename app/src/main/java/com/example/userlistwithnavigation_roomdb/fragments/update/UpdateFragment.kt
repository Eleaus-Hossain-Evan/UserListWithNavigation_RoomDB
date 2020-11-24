package com.example.userlistwithnavigation_roomdb.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userlistwithnavigation_roomdb.R
import com.example.userlistwithnavigation_roomdb.model.User
import com.example.userlistwithnavigation_roomdb.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.etUpdateFirstName.setText(args.currentUser.firstName)
        view.etUpdateLastName.setText(args.currentUser.lasttName)
        view.etUpdatetAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener {
            updateItem()
        }

        //Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem(){
        val firstName = etUpdateFirstName.text.toString()
        val lastName = etUpdateLastName.text.toString()
        val age = Integer.parseInt(etUpdatetAge.text.toString())

        if(inputChack(firstName, lastName,etUpdatetAge.text)){
            //Create user object
            val updateUser = User(args.currentUser.id, firstName,lastName,age)
            //Update current user
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please filled out all fields..", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputChack(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detele_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes"){ _, _ ->
                    mUserViewModel.deleteUser(args.currentUser)
                    Toast.makeText(requireContext(),
                            "Successfully remove: ${args.currentUser.firstName} ..",
                            Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                }
                .setNegativeButton("No"){ _, _ ->}
                .setTitle("Delete ${args.currentUser.firstName}")
                .setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
                .create()
                .show()

    }
}

