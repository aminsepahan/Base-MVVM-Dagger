package com.amin.sample.ui.imgur

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amin.sample.utils.EnumCompanion
import java.lang.ref.WeakReference

class ImgurFragAdapter(
    fm: FragmentManager,
    private val fragList: MutableList<ImgurListFrag>,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    fun getPageTitle(position: Int): CharSequence {
        return SectionType.fromInt(position)!!.getTitle()
    }

    enum class SectionType(val value: Int) {
        HOT(0) {
            override fun getTitle() = "Hot"
        },
        TOP(1) {
            override fun getTitle() = "Top"
        },
        USER(2) {
            override fun getTitle() = "User"
        };

        abstract fun getTitle(): String

        companion object : EnumCompanion<Int, SectionType>(values().associateBy(SectionType::value))
    }

    override fun getItemCount(): Int {
        return fragList.size
    }

    fun getItem(position: Int) = fragList[position]

    override fun createFragment(position: Int): Fragment {
        return fragList[position]
    }
}