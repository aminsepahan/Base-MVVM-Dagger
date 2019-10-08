package com.amin.sample.ui.imgur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.amin.sample.R
import com.amin.sample.base.BaseFragment
import com.amin.sample.utils.extensions.showKeyboard
import com.amin.sample.utils.extensions.string
import kotlinx.android.synthetic.main.frag_imgur_container.*
import kotlinx.android.synthetic.main.frag_imgur_container.view.*

class ImgurContainerFrag : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_imgur_container, container, false)
        view.vp.offscreenPageLimit = 2
        view.vp.adapter = ImgurFragAdapter(childFragmentManager, mutableListOf(
            ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.HOT)),
            ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.TOP)),
            ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.USER))
        ))
        view.tabs.setupWithViewPager(view.vp)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                (vp.adapter as ImgurFragAdapter?)?.let {
                    setListClickListener(it)
                }
            }
        })
    }

    private fun setListClickListener(it: ImgurFragAdapter) {
        subscribe = (it.instantiateItem(
            vp,
            vp.currentItem
        ) as ImgurListFrag?)?.clickEvent?.subscribe { model ->
            findNavController().navigate(ImgurContainerFragDirections.actionImgurContainerFragToDetailsFragment(model))
        }
    }
}

