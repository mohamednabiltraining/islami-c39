package com.route.islamic39.ui.home.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamic39.R
import com.route.islamic39.databinding.FragmentTasbehBinding

class TasbehFragment : Fragment() {

    lateinit var viewBinding: FragmentTasbehBinding
    var counter = 0
    var currentDhikrIndex = 0
    lateinit var azkarList: MutableList<String>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTasbehBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        azkarList = resources.getStringArray(R.array.azkarList).toMutableList()
        viewBinding.dhikrTv.text = azkarList[currentDhikrIndex]
        onSebhaClick()
    }

    private fun onSebhaClick() {
        viewBinding.bodyOfSebhaImv.setOnClickListener {
            viewBinding.bodyOfSebhaImv.rotation += (360 / 33).toFloat()
            if (counter < 33)
                counter++
            else {
                counter = 0
                currentDhikrIndex =
                    if (currentDhikrIndex < azkarList.size - 1) ++currentDhikrIndex else 0
                viewBinding.dhikrTv.text = azkarList[currentDhikrIndex]
            }
            viewBinding.counterTv.text = counter.toString()
        }
    }
}