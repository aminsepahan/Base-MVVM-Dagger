package com.amin.homitel.ui.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.amin.homitel.R
import com.amin.homitel.databinding.FragmentPostsBinding
import com.amin.homitel.ui.posts.PostsViewModel


class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_posts, container, false
        )
        val view = binding.root
        //here data must be an instance of the class MarsDataProvider
        binding.viewModel = viewModel
        return view
    }

}
