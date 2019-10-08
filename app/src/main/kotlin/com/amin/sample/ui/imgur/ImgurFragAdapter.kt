package com.amin.sample.ui.imgur

import android.view.ViewGroup
import androidx.fragment.app.*
import com.amin.sample.R
import com.amin.sample.base.App
import com.amin.sample.utils.EnumCompanion

class ImgurFragAdapter(
    fm: FragmentManager,
    val fragList: MutableList<ImgurListFrag>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getCount(): Int {
        return fragList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return SectionType.fromInt(position)!!.getTitle()
    }

    enum class SectionType(val value: Int) {
        HOT(0){
            override fun getTitle() = "Hot"
        },
        TOP(1){
            override fun getTitle() = "Top"
        },
        USER(2){
            override fun getTitle() = "User"
        };

        abstract fun getTitle(): String
        companion object : EnumCompanion<Int, SectionType>(values().associateBy(SectionType::value))
    }
}