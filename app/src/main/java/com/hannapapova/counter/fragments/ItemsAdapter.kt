package com.hannapapova.counter.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hannapapova.counter.R
import com.hannapapova.counter.room.Item
import com.hannapapova.counter.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.item_row.view.*

class ItemsAdapter(val viewModelStoreOwner: ViewModelStoreOwner) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var itemsList = emptyList<Item>()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rowLayout: ConstraintLayout = itemView.row_layout
        private val textViewName = itemView.tv_name
        private val textViewCounter = itemView.tv_counter
        private val buttonDecrease = itemView.btn_decrease
        private val buttonIncrease = itemView.btn_increase
        private val itemViewModel =
            ViewModelProvider(viewModelStoreOwner).get(ItemViewModel::class.java)

        fun bind(item: Item) {
            textViewName.text = item.name
            textViewCounter.text = item.counter.toString()

            rowLayout.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
                itemView.findNavController().navigate(action)
            }

            buttonDecrease.setOnClickListener {
                val newCounter = item.counter - 1
                notifyDataSetChanged()
                val newItem = Item(item.id, item.name, newCounter)
                itemViewModel.updateItem(newItem)
            }

            buttonIncrease.setOnClickListener {
                val newCounter = item.counter + 1
                notifyDataSetChanged()
                val newItem = Item(item.id, item.name, newCounter)
                itemViewModel.updateItem(newItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(itemsList[position])


    fun setData(items: List<Item>) {
        this.itemsList = items
        notifyDataSetChanged()
    }
}