package com.example.unimap.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unimap.R
import com.example.unimap.Volunteer
import com.example.unimap.VolunteerAdapter

class VolunteerFragment : Fragment() {

    private lateinit var volunteerRecyclerView : RecyclerView
    private lateinit var newArrayList: ArrayList<Volunteer>
    lateinit var imageId:Array<Int>
    lateinit var names:Array<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i
        )
        names = arrayOf(
            "John Doe",
            "Jane Doe",
            "Amily Kent",
            "Chris Prat",
            "Jenniffer Aniston",
            "Patrick Bateman",
            "Hugh Jackman",
            "Tom Hardy",
            "Simon Giertz"
        )
        volunteerRecyclerView = view.findViewById(R.id.volunteerRecyclerView)
        volunteerRecyclerView.layoutManager = LinearLayoutManager(requireContext())//activity : returns the context of this fragment since activity is an extension of context
        volunteerRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<Volunteer>()
        getUserData()


    }
private fun getUserData(){
    for (i in imageId.indices){
        val volunteer = Volunteer(imageId[i],names[i])
        newArrayList.add(volunteer)
    }
    volunteerRecyclerView.adapter = VolunteerAdapter(newArrayList)
}
}