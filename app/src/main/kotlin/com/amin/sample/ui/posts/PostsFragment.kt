package com.amin.sample.ui.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.amin.sample.R
import com.amin.sample.databinding.FragmentPostsBinding
import com.amin.sample.model.Post
import com.amin.sample.utils.LDR


class PostsFragment : Fragment() {

    private lateinit var binding: FragmentPostsBinding
    private lateinit var viewModel: PostsViewModel
    val postListAdapter: PostListAdapter = PostListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = PostViewModelFactory()

        viewModel = ViewModelProviders.of(this, factory).get(PostsViewModel::class.java)
        viewModel.postLiveData.observe(this, this.dataObserver)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_posts, container, false
        )
        val view = binding.root

        binding.rv.layoutManager = LinearLayoutManager(activity)
        binding.rv.hasFixedSize()
        binding.viewModel = viewModel
        binding.postListAdapter = postListAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPosts()
    }

    private val dataObserver = Observer<LDR<List<Post>>> { result ->
        when (result?.status) {
            LDR.Status.LOADING -> {
                // Loading data
            }

            LDR.Status.ERROR -> {
                // Error for http request
            }

            LDR.Status.SUCCESS -> {
                postListAdapter.updatePostList(result.response!!)
            }
            else -> {
            }
        }
    }

}
