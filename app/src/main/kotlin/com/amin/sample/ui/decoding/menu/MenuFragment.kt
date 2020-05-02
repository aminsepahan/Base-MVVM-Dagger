package com.amin.sample.ui.menu


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amin.sample.R
import com.bumptech.glide.Glide
import com.github.florent37.viewanimator.ViewAnimator
import com.github.florent37.viewanimator.ViewAnimator.RESTART
import kotlinx.android.synthetic.main.frag_menu.*


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(R.drawable.tv_1).into(gif)
        decodeFile1.setOnClickListener {
            openDecodingFrag(R.raw.file_1)
        }
        decodeFile2.setOnClickListener {
            openDecodingFrag(R.raw.file_2)
        }
        decodeFile3.setOnClickListener {
            openDecodingFrag(R.raw.file_3)
        }
        ViewAnimator.animate(decodeFile1).duration(800).fadeOut().repeatMode(RESTART).repeatCount(-1).start()
    }

    private fun openDecodingFrag(fileId: Int) {
        findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToDecodingMessage(fileId))
    }


}
