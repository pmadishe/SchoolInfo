package com.example.schoolinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolinfo.databinding.SchoolListItemBinding
import com.example.schoolinfo.model.ResponseModelItem
import com.example.schoolinfo.ui.FirstFragmentDirections

class SchoolListAdapter (
    private val navController: NavController,
    private val onItemClick: (ResponseModelItem) -> Unit
) :
    RecyclerView.Adapter<SchoolListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SchoolListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseModelItem) {
            binding.apply {
                schoolName.text = item.school_name
                dbn.text=item.dbn

                binding.root.setOnClickListener {
                    onItemClick(item)
                    val action =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(item.dbn)
                    navController.navigate(action)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<ResponseModelItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem.school_name == newItem.school_name
        }

        override fun areContentsTheSame(
            oldItem: ResponseModelItem,
            newItem: ResponseModelItem
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SchoolListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = differ.currentList.size
}
