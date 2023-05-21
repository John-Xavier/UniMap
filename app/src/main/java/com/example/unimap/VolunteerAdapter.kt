package com.example.unimap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class VolunteerAdapter(private val volunteerList:ArrayList<Volunteer>):
    RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.volunteer_card,parent,false)
        return VolunteerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return volunteerList.size
    }

    override fun onBindViewHolder(holder: VolunteerViewHolder, position: Int) {
        val currentItem = volunteerList[position]
        holder.profilePic.setImageResource(currentItem.profilePicture)
        holder.volunteerName.text = currentItem.name
    }
    class VolunteerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val profilePic : ShapeableImageView = itemView.findViewById(R.id.volunteer_image)
        val volunteerName : TextView = itemView.findViewById(R.id.volunteerName)
    }
}