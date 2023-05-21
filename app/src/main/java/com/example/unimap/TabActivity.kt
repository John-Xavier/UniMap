package com.example.unimap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TabActivity : AppCompatActivity() {

private lateinit var tabLayout: TabLayout
private lateinit var viewPager: ViewPager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)

//        tabLayout.addTab(tabLayout.newTab().setText("Volunteer"))
//        tabLayout.addTab(tabLayout.newTab().setText("Map"))
//        tabLayout.addTab(tabLayout.newTab().setText("Profile"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = TabAdapter(this,supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


    }
}