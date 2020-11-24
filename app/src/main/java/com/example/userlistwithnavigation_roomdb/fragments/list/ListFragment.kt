package com.example.userlistwithnavigation_roomdb.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userlistwithnavigation_roomdb.R
import com.example.userlistwithnavigation_roomdb.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            //get all user data into recyclerview
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener(View.OnClickListener {
            //Navigate to Add fragment
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        })

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detele_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
         deleteAllUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
                .setPositiveButton("Yes"){ _, _ ->
                    mUserViewModel.deleteAllUser()
                    Toast.makeText(requireContext(),
                            "Successfully removed ..",
                            Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No"){ _, _ ->}
                .setTitle("Delete Everything!!")
                .setMessage("Are you sure you want to delete everything?")
                .create()
                .show()
    }
}