package com.amin.sample.ui.message


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.amin.sample.R
import kotlinx.android.synthetic.main.frag_message.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MessageFragment : Fragment() {

    val  args by navArgs<MessageFragmentArgs>()
    var skipped = false
    var i = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_message, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        message.setOnClickListener {
            if (!skipped) {
                skipped = true
            } else {
                lifecycleScope.launch {
                    i++
                    typeMessage(args.messages[i])
                }
            }
        }
        lifecycleScope.launch {
            do{
                skipped = false
                typeMessage(args.messages[i])
                i++
            } while (i < args.messages.size)
        }

    }

    @SuppressLint("SetTextI18n")
    private suspend fun typeMessage(it: String) {
        delay(1500)
        message.text = ""
        it.forEach { char ->
            message.text = "${message.text}$char"
            if (!skipped) {
                delay(50)
            }
        }
    }


}
