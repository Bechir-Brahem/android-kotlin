package com.example.tp3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp3.enums.Gender
import com.example.tp3.model.User
import com.mikhaellopez.circularimageview.CircularImageView

class UserAdapter(var users: ArrayList<User> , var clickListener: ItemClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView = itemView.findViewById(R.id.name)
        var image : CircularImageView = itemView.findViewById(R.id.icon)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: UserAdapter.ViewHolder, position: Int) {
        val user : User = users[position]
        val name = viewHolder.name
        val imageView = viewHolder.image
        viewHolder.itemView.setOnClickListener {
            this.clickListener.onItemClick(user)
        }
        name.text = user.name
        if(user.gender == Gender.MALE)
            imageView.setImageResource(R.drawable.male)
        else
            imageView.setImageResource(R.drawable.female)
    }

    override fun getItemCount(): Int {
        return users.size
    }
    interface ItemClickListener{
        fun onItemClick(user : User)
    }
}
