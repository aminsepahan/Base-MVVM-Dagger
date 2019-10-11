package com.amin.sample.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.amin.sample.R
import com.amin.sample.databinding.FragDetailsBinding
import com.amin.sample.utils.extensions.gone


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.frag_details, container, false
        )
        binding.model = args.model
        if (args.model.model != null) {
            binding.imgurData = args.model.model
        } else {
            binding.imgurDetails.gone()
        }

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        ViewCompat.setTransitionName(binding.coverImageView, args.model.imageTransition)
        if (args.model.titleTransition != null) {
            ViewCompat.setTransitionName(binding.caption, args.model.titleTransition)
        }
        return binding.root
    }


}
