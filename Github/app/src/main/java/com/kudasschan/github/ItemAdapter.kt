package com.kudasschan.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

class ItemAdapter(private val listUser: ArrayList<ItemData>) :
    RecyclerView.Adapter<ItemAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClick(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtName: TextView = itemView.findViewById(R.id.txt_name)
        var txtUsername: TextView = itemView.findViewById(R.id.txt_username)
        var profilePict: CircleImageView = itemView.findViewById(R.id.profile_image)
        fun bind(data: ItemData) {
            Glide.with(itemView.context)
                .load(data.avatar)
                .into(profilePict)
            txtName.text = data.name
            txtUsername.text = data.username
        }
    }

    interface OnItemClickCallback {
        fun onItemClick(data: ItemData)
    }
}