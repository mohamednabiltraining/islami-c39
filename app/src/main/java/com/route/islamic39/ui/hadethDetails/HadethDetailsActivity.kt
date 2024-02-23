package com.route.islamic39.ui.hadethDetails

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.route.islamic39.databinding.ActivityHadethDetailsBinding
import com.route.islamic39.model.Hadeth
import com.route.islamic39.ui.Constants

class HadethDetailsActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHadethDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHadethDetailsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        extractParams()
        initViews()
    }

    private fun initViews() {
        setTitle(null)
        setSupportActionBar(viewBinding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        viewBinding.content.hadithTitle.text = hadeth?.title
        viewBinding.content.content.text = hadeth?.content
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    var hadeth: Hadeth? = null

    private fun extractParams() {
        hadeth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.Hadeth_EXTRA, Hadeth::class.java)
        } else {
            intent.getParcelableExtra<Hadeth>(Constants.Hadeth_EXTRA)
        }
    }
}
