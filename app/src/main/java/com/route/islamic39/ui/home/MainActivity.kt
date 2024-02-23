package com.route.islamic39.ui.home

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.route.islamic39.R
import com.route.islamic39.dataStore.DataStoreManager
import com.route.islamic39.dataStore.MainViewModel
import com.route.islamic39.databinding.ActivityMainBinding
import com.route.islamic39.ui.home.hadeth.HadethFragment
import com.route.islamic39.ui.home.quran.QuranFragment
import com.route.islamic39.ui.home.radio.RadioFragment
import com.route.islamic39.ui.home.tasbeh.TasbehFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var dataStore: DataStoreManager
    private fun isDarkMode(): Boolean {
        return when (
            baseContext.resources.configuration.uiMode.and(
                Configuration.UI_MODE_NIGHT_MASK
            )
        ) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        dataStore = DataStoreManager(this)
        changeAppMode()
        initViews()
    }

    private fun changeAppMode() {
        viewBinding.apply {
            viewModel.getMode.observe(this@MainActivity) { mode ->
                when (mode) {
                    true -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }

                    false -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
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
        viewBinding.nightModeSwitcher.setOnClickListener {
            when (isDarkMode()) {
                true -> viewModel.saveMode(false)
                false -> viewModel.saveMode(true)
            }
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
