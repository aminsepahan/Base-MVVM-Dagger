package com.amin.sample.ui.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amin.sample.R
import com.amin.sample.base.BaseFragment
import com.amin.sample.base.Configs
import com.amin.sample.base.Configs.SampleTypes.*
import com.amin.sample.model.MainMenuItem
import com.amin.sample.utils.extensions.showHideFade
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.frag_circular_menu.*
import kotlinx.android.synthetic.main.frag_circular_menu.view.*

@Suppress("unused")
class CircularMenuFrag : BaseFragment() {

    private var currentMenu: MainMenuItem? = null
    private var listAdapter = MainMenuAdapter()
    private var currentSlice: Int = 0
    private var initX: Float = 0f
    private var rotation = 0f
    private var count = 8
    private var sliceAngel = 360 / count
    private var halfSliceAngel = sliceAngel / 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_circular_menu, container, false)
        val modelList: MutableList<MainMenuItem> = mutableListOf()
        for (i in 0 until Configs.effectiveSize) {
            modelList.add(MainMenuItem.createMenuItem(Configs.SampleTypes.fromInt(i)!!))
        }
        view.rv.layoutManager = LinearLayoutManager(activity)
        view.rv.setHasFixedSize(true)
        view.rv.adapter = listAdapter.apply {
            setModelListForAdapter(modelList)
        }
        subscribe = listAdapter.clickEvent
            .subscribe {
                lunchSampleModes(it)
            }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWheel()
        val listener = View.OnTouchListener(function = { touchView, event ->
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    initX = event.rawX
                    ViewAnimator.animate(pin).scale(1f, 0.7f).duration(200).start()
                }
                MotionEvent.ACTION_MOVE -> {
                    touchView.rotation =
                        rotation + (-(initX - event.rawX) * 180 / touchView.width)
                }
                MotionEvent.ACTION_UP -> {
                    rotation = touchView.rotation
                    movePrecisely()
                }
            }
            true
        })
        menuWheel.setOnTouchListener(listener)
        lunchBtn.setOnClickListener {
            Configs.SampleTypes.fromInt(currentSlice).apply {
                lunchSampleModes(this!!)
            }
        }
    }

    private fun lunchSampleModes(sampleMode: Configs.SampleTypes) {
        Configs.sampleMode = sampleMode
        when (sampleMode) {
            SHUTTER_STOCK -> findNavController().navigate(CircularMenuFragDirections.actionCircularMenuFragToShutterStockListFrag())
            IMGUR -> findNavController().navigate(CircularMenuFragDirections.actionCircularMenuFragToImgurContainerFrag())
            BLOG_POSTS -> findNavController().navigate(CircularMenuFragDirections.actionCircularMenuFragToPostsFragment())
            else -> { }
        }
    }

    override fun onStart() {
        super.onStart()
        menuWheel.rotation = rotation
    }

    private fun setWheel() {
        menuWheel.rotation = 0F
    }

    private fun movePrecisely() {
        val rotationRadius = (rotation).rem(sliceAngel)
        startAnimation(
            if (rotationRadius > halfSliceAngel) {
                halfSliceAngel - (rotationRadius - halfSliceAngel)
            } else {
                -rotationRadius
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun updateScreen() {
        if (currentSlice < Configs.effectiveSize) {
            showEmpty(false)
            currentMenu =
                MainMenuItem.createMenuItem(Configs.SampleTypes.fromInt(currentSlice)!!).apply {
                    infoLogo.setImageResource(imageId)
                    infoTitle.text = title
                    infoDesc.text = description
                }
            infoCV.showHideFade(true)
        } else {
            showEmpty(true)
        }
    }

    private fun startAnimation(rotationRadius: Float) {

        ViewAnimator.animate(menuWheel)
            .rotation(rotation + rotationRadius)
            .decelerate().duration(200)
            .onStop {
                val rotatedSlices = if (rotation >= 0 || rotation <= -sliceAngel) {
                    if (rotation > 0) ((rotation + 1).rem(360) / sliceAngel).toInt() else ((rotation - 1).rem(
                        360
                    ) / sliceAngel).toInt()
                } else {
                    -1
                }
                currentSlice = if (rotatedSlices >= 0) {
                    rotatedSlices
                } else {
                    count + rotatedSlices
                }
                updateScreen()
            }
            .thenAnimate(pin).scale(0.7f, 1f).duration(200)
            .start()
        rotation += rotationRadius
    }

    private fun showEmpty(showOrHide: Boolean) {
        if (showOrHide) {
            infoCV.showHideFade(false, goneOrInvisible = false)
            idleTv.text = getString(R.string.empty_slot)
        }
        idleTv.showHideFade(showOrHide)
    }
}

