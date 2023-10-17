package com.example.schoolinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolinfo.databinding.SchoolListItemBinding
import com.example.schoolinfo.model.ResponseModelItem

class SchoolListAdapter(
    private val onItemClick: (ResponseModelItem) -> Unit
) : ListAdapter<ResponseModelItem, SchoolListAdapter.SchoolViewHolder>(SchoolDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SchoolListItemBinding.inflate(inflater, parent, false)
        return SchoolViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = getItem(position)
        holder.bind(school)
    }

    inner class SchoolViewHolder(private val binding: SchoolListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResponseModelItem) {
            binding.apply {
                schoolName.text = item.school_name
                dbn.text = item.dbn
            }
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}

class SchoolDiffCallback : DiffUtil.ItemCallback<ResponseModelItem>() {
    override fun areItemsTheSame(oldItem: ResponseModelItem, newItem: ResponseModelItem): Boolean {
        return oldItem.dbn == newItem.dbn
    }

    override fun areContentsTheSame(oldItem: ResponseModelItem, newItem: ResponseModelItem): Boolean {
        return oldItem == newItem
    }
}

