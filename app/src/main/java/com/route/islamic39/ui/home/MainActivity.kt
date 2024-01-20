package com.route.islamic39.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.route.islamic39.R
import com.route.islamic39.databinding.ActivityMainBinding
import com.route.islamic39.ui.home.hadeth.HadethFragment
import com.route.islamic39.ui.home.quran.QuranFragment
import com.route.islamic39.ui.home.radio.RadioFragment
import com.route.islamic39.ui.home.tasbeh.TasbehFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.content
            .bottomNav
            .setOnItemSelectedListener { item ->
                val fragment: Fragment = when (item.itemId) {
                    R.id.navigation_quran -> {
                        QuranFragment()
                    }

                    R.id.navigation_hadeth -> {
                        HadethFragment()
                    }

                    R.id.navigation_tasbeh -> {
                        TasbehFragment()
                    }

                    R.id.navigation_radio -> {
                        RadioFragment()
                    }

                    else -> {
                        QuranFragment()
                    }
                }
                pushFragment(fragment)
                true
            }
        viewBinding.content.bottomNav
            .selectedItemId = R.id.navigation_quran
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
