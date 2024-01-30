package com.route.islamic39.ui.home.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamic39.R
import com.route.islamic39.databinding.FragmentTasbehBinding

/**
 * mohamed afify
 * developer branch*/

class TasbehFragment:Fragment() {
    private var counter = 0
    private var dhikerIndex = 0
    //  var des : Int = 50
    private lateinit var azkarList : MutableList<String>

    private lateinit var viewBinding: FragmentTasbehBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTasbehBinding.inflate(inflater,container,false)
        return viewBinding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        azkarList = resources.getStringArray(R.array.azkerList).toMutableList()
        onSebhaClick()
    }

    private fun onSebhaClick() {
        viewBinding.mainSephaLogo.setOnClickListener{
            viewBinding.mainSephaLogo.rotation +=(360/33).toFloat()
            if (counter<33) {
                counter++
            } else{
                counter = 0
                dhikerIndex = if (dhikerIndex <azkarList.size-1) ++dhikerIndex
                else 0
                viewBinding.dithkr.text = azkarList[dhikerIndex]
            }
            viewBinding.counter.text = counter.toString()
        }
    }
}
