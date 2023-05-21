package com.example.unimap

import android.content.Context
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.unimap.Fragments.MapFragment
import com.example.unimap.Fragments.ProfileFragment
import com.example.unimap.Fragments.VolunteerFragment
import androidx.viewpager2.adapter.FragmentStateAdapter as FragmentStateAdapter

internal class TabAdapter(var context: Context,fm:FragmentManager, var totalTabs:Int):FragmentPagerAdapter(fm){


    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->  return VolunteerFragment()
            1->  return MapFragment()
            2->  return ProfileFragment()
            else ->{
                return MapFragment()
            }
        }
    }
}