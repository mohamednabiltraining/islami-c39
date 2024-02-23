package com.route.islamic39.ui.home.radio

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.route.islamic39.R
import com.route.islamic39.api.APIManager
import com.route.islamic39.api.model.radioResponses.Radio
import com.route.islamic39.api.model.radioResponses.RadioResources
import com.route.islamic39.databinding.FragmentRadioBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    private lateinit var viewBinding: FragmentRadioBinding
    private var mediaPlayer: MediaPlayer? = null
    private var currentPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRadioBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getRadioChannels()
    }

    private fun getRadioChannels() {
        APIManager
            .getServices()
            .getRadioResources()
            .enqueue(object : Callback<RadioResources> {
                override fun onResponse(
                    call: Call<RadioResources>,
                    response: Response<RadioResources>
                ) {
                    when (response.isSuccessful) {
                        true -> initViews(response.body()?.radios)
                        else -> {
                            showDialog("Failed to load radio channels")
                        }
                    }
                }

                override fun onFailure(call: Call<RadioResources>, t: Throwable) {
                    showDialog(t.message)
                }
            })
    }

    private fun showDialog(message: String?) {
        val errorAlertDialog = AlertDialog.Builder(requireContext())
        errorAlertDialog
            .setCancelable(false)
            .setTitle("Loading Channel Error")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
        errorAlertDialog.show()
    }

    private fun initViews(radios: List<Radio>?) {
        mediaPlayer = MediaPlayer()
        viewBinding.btnPlay.setOnClickListener {
            mediaPlayer?.let { player ->
                when (player.isPlaying) {
                    false -> {
                        player.reset()
                        playChannel(radios?.get(currentPosition))
                    }

                    else -> {
                        player.pause()
                        viewBinding.btnPlay.setImageResource(R.drawable.ic_play)
                    }
                }
            }
        }
        viewBinding.btnNext.setOnClickListener {
            getNextChannel(radios?.size)
            mediaPlayer?.reset()
            playChannel(radios?.get(currentPosition))
        }
        viewBinding.btnBack.setOnClickListener {
            getPreviousChannel(radios?.size)
            mediaPlayer?.reset()
            playChannel(radios?.get(currentPosition))
        }
    }

    private fun getPreviousChannel(size: Int?) {
        currentPosition--
        size?.let {
            if (currentPosition < 0) {
                currentPosition = it - 1
            }
        }
    }

    private fun getNextChannel(size: Int?) {
        currentPosition++
        size?.let {
            if (currentPosition >= it) {
                currentPosition = 0
            }
        }
    }

    private fun playChannel(currentChannel: Radio?) {
        viewBinding.radioName.text = currentChannel?.name
        mediaPlayer?.apply {
            setDataSource(currentChannel?.url)
            prepare()
            start()
        }
        viewBinding.btnPlay.setImageResource(R.drawable.ic_pause)
    }
}
