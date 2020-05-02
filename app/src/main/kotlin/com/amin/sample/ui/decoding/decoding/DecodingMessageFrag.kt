package com.amin.sample.ui.decoding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amin.sample.R
import com.amin.sample.utils.decoder.WavDecoder
import com.amin.sample.utils.extensions.string
import com.bumptech.glide.Glide
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.frag_decoding.*
import kotlinx.coroutines.*


class DecodingMessageFrag : Fragment() {
    private var job: Job? = null
    private val args by navArgs<DecodingMessageFragArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_decoding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).asGif().load(R.drawable.tv_2).into(gif)
        finish.setOnClickListener {
            startDecoding()
        }
    }

    private fun startDecoding() {
        finish.setOnClickListener { }
        ViewAnimator.animate(finish).fadeOut()
            .onStop { finish.text = getString(R.string.show_messages) }.duration(300).start()
        val bytes = resources.openRawResource(args.fileId).readBytes()
        val decoder = WavDecoder {
            MainScope().launch {
                updateUi(it)
            }
        }

        job = GlobalScope.launch {
            val binaryStream = async { decoder.convertWavSignalsToBinary(bytes) }
            val leadOffset = async { decoder.findLeadOffset(binaryStream.await()) }
            val chars = async {
                decoder.convertBinaryStreamToByteStream(
                    binaryStream.await(),
                    leadOffset.await()
                )
            }
            val messages = async { decoder.getMessages(chars.await().substring(3)) }
            showFinishedButton(messages.await())
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }

    private fun showFinishedButton(messages: List<String>) {
        finish.setOnClickListener {
            findNavController().navigate(
                DecodingMessageFragDirections.actionDecodingMessageToMessageFragment(
                    messages.toTypedArray()
                )
            )
        }
    }

    private fun updateUi(
        data: ProcessingDataModel
    ) = with(data) {
        job?.takeIf { it.isCancelled }?.let {
            return Unit
        }
        progress /= 100
        currentBit.takeIf { it != '_' }?.let {
            currentBitValTv.text =
                if (currentBitValTv.text.length > 10)
                    currentBitValTv.text.substring(1) + currentBit
                else
                    currentBitValTv.string() + currentBit
        }
        changeText(currentDirection, currentUpDownValTv)
        changeText(currentByte.toString(), currentByteValTv)
        currentChar.takeIf { it != '_' }?.let {
            currentCharValTv.text =
                if (currentCharValTv.text.length > 30)
                    currentCharValTv.text.substring(1) + it
                else
                    currentCharValTv.string() + it
        }
        changeText("${progress}%", currentProgressValTv)
        finish.alpha = (progress.toFloat() / 100)
        view?.invalidate()
    }

    private fun changeText(result: String, textView: TextView) {
        result.takeIf {
            it != "_" && it != "-1"
        }?.let {
            activity?.runOnUiThread {
                textView.text = it
            }
        }
    }

}
