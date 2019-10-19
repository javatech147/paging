package com.example.paging.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.R
import com.example.paging.api.Owner
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class OwnerPagedListAdapter : PagedListAdapter<Owner, OwnerPagedListAdapter.OwnerViewHolder>(

        object : DiffUtil.ItemCallback<Owner>() {
            override fun areItemsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem.user_id == newItem.user_id
            }

            override fun areContentsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem == newItem
            }
        }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OwnerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false);
        return OwnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OwnerViewHolder, position: Int) {
        val owner: Owner? = getItem(position)
        holder.setData(owner)
    }


    inner class OwnerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(owner: Owner?) {
            owner?.let {
                itemView.tvName.text = it.display_name
                itemView.tvContactNumber.text = it.reputation.toString()
                Picasso.get().load(it.profile_image).into(itemView.ivPersonImage)
            }
        }
    }
}