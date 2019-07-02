package com.amin.homitel.ui.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amin.homitel.R
import com.amin.homitel.databinding.FragmentPostsBinding
import com.amin.homitel.model.Post
import com.amin.homitel.utils.LiveDataResult
import com.amin.homitel.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_posts.*


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
        binding.viewModel = viewModel
        binding.postListAdapter = postListAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPosts()
    }

    private val dataObserver = Observer<LiveDataResult<List<Post>>> { result ->
        when (result?.status) {
            LiveDataResult.Status.LOADING -> {
                // Loading data
            }

            LiveDataResult.Status.ERROR -> {
                // Error for http request
            }

            LiveDataResult.Status.SUCCESS -> {
                rv.visible()
                postListAdapter.updatePostList(result.data!!)
            }
        }
    }

}

