package com.hannapapova.counter.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannapapova.counter.R
import com.hannapapova.counter.room.Item
import com.hannapapova.counter.tools.fragmentHideKeyboard
import com.hannapapova.counter.tools.userInputIsCorrect
import com.hannapapova.counter.viewModel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_name.setText(args.currentItem.name)

        btn_update.setOnClickListener {
            updateItem()
            fragmentHideKeyboard(requireContext(), view)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_single_counter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            itemViewModel.deleteItem(args.currentItem)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            view?.let { fragmentHideKeyboard(requireContext(), it) }
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle("${getString(R.string.alert_delete_single_counter)} ${args.currentItem.name}?")
        builder.create().show()
    }

    private fun updateItem() {
        val newName = et_name.text.toString()

        if(userInputIsCorrect(newName)){
            val item = Item(args.currentItem.id, newName, args.currentItem.counter)
            itemViewModel.updateItem(item)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else{
            Toast.makeText(context, getString(R.string.counter_without_name_toast), Toast.LENGTH_SHORT).show()
        }
    }
}