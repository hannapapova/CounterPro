package com.hannapapova.counter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hannapapova.counter.R
import com.hannapapova.counter.room.Item
import com.hannapapova.counter.tools.fragmentHideKeyboard
import com.hannapapova.counter.tools.userInputIsCorrect
import com.hannapapova.counter.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*

class AddFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add.setOnClickListener {
            insertDataToDatabase()
            fragmentHideKeyboard(requireContext(), view)
        }
    }

    private fun insertDataToDatabase() {
        val name = et_name.text.toString()

        if(userInputIsCorrect(name)){
            val item = Item(0, name, 0)
            itemViewModel.addItem(item)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else{
            Toast.makeText(context, getString(R.string.counter_without_name_toast), Toast.LENGTH_SHORT).show()
        }
    }
}