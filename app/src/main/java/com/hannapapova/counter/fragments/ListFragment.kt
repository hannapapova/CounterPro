package com.hannapapova.counter.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hannapapova.counter.R
import com.hannapapova.counter.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.rv_counters
        val itemsAdapter = ItemsAdapter(this)
        recyclerView.adapter = itemsAdapter

        itemViewModel.readAllData.observe(viewLifecycleOwner, Observer{
            itemsAdapter.setData(it)
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.counters_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            deleteAll()
        }
        if (item.itemId == R.id.menu_add) {
            addItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes") { _, _ ->
            itemViewModel.deleteAllItems()
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete all counters?")
        builder.create().show()
    }

    private fun addItem(){
        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }
}