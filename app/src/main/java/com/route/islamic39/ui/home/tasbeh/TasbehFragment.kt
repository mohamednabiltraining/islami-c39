package com.route.islamic39.ui.home.tasbeh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.route.islamic39.databinding.FragmentTasbehBinding

class TasbehFragment : Fragment() {

    lateinit var viewBinding: FragmentTasbehBinding

    private var counter = 0
    private var sebhaFullSpin = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTasbehBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.sebhaCounter.text = counter.toString()
        viewBinding.sebhaBtn.text = "سبحان الله"
        viewBinding.sebhaBtn.setOnClickListener {
            viewBinding.bodyOfSebha.rotation = viewBinding.bodyOfSebha.rotation + 5F
            counter++
            viewBinding.sebhaCounter.text = counter.toString()
            if (counter == 33) {
                sebhaFullSpin++
                counter = 0
                viewBinding.sebhaCounter.text = counter.toString()
            }
            when (sebhaFullSpin) {
                1 -> {
                    viewBinding.sebhaBtn.text = "الحمد لله"
                }
                2 -> {
                    viewBinding.sebhaBtn.text = "الله اكبر"
                }
                3 -> {
                    viewBinding.sebhaBtn.text = "سبحان الله"
                    sebhaFullSpin = 0
                    counter = 0
                }
            }
        }
    }
}
