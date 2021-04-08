package com.hannapapova.counter.fragments

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hannapapova.counter.R
import com.hannapapova.counter.room.Item
import com.hannapapova.counter.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add, container, false)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val name = et_name.text.toString()

        //TODO: Check if name field is empty

        val item = Item(0, name, 0)
        itemViewModel.addItem(item)

        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}