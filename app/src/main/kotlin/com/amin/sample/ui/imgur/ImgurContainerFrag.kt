package com.amin.sample.ui.imgur

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.amin.sample.R
import com.amin.sample.base.BaseFragment
import com.amin.sample.model.DetailFragData
import kotlinx.android.synthetic.main.frag_imgur_container.*
import kotlinx.android.synthetic.main.frag_imgur_container.view.*
import com.google.android.material.tabs.TabLayoutMediator

class ImgurContainerFrag : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_imgur_container, container, false)
        view.vp.offscreenPageLimit = 2
        view.vp.adapter = ImgurFragAdapter(
            childFragmentManager, mutableListOf(
                ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.HOT)),
                ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.TOP)),
                ImgurListFrag.newInstance(ImgurListFragArgs(ImgurFragAdapter.SectionType.USER))
            ), lifecycle
        )
        TabLayoutMediator(view.tabs, view.vp) {tab, position ->
            tab.text = (view.vp.adapter as ImgurFragAdapter).getPageTitle(position)
        }.attach()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.vp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                (vp.adapter as ImgurFragAdapter?)?.let {
                    setListClickListener(it.getItem(position))
                }
            }
        })
    }

    private fun setListClickListener(currentFrag: ImgurListFrag) {
        subscribe = currentFrag.clickEvent.subscribe { model ->
            val extras = if (model.titleTransition != null) {
                FragmentNavigatorExtras(
                    model.imageTransition to model.imageTransition.transitionName,
                    model.titleTransition to model.titleTransition.transitionName
                )
            } else {
                FragmentNavigatorExtras(
                    model.imageTransition to model.imageTransition.transitionName
                )
            }
            findNavController().navigate(
                ImgurContainerFragDirections.actionImgurContainerFragToDetailsFragment(
                    DetailFragData(
                        model.model,
                        model.imageUrl,
                        model.title,
                        model.imageTransition.transitionName,
                        model.titleTransition?.transitionName
                    )
                ),
                extras
            )
        }
    }
}

