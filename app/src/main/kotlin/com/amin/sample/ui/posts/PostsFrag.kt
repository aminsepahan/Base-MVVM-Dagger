package com.amin.sample.ui.posts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.amin.sample.R
import com.amin.sample.base.BaseFragment
import com.amin.sample.databinding.FragPostsBinding
import com.amin.sample.model.DetailFragData
import com.amin.sample.model.Post
import com.amin.sample.utils.LDR


class PostsFrag : BaseFragment() {

    private lateinit var binding: FragPostsBinding
    private lateinit var viewModel: PostsViewModel
    private val listAdapter: PostListAdapter = PostListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = PostViewModelFactory()

        viewModel = ViewModelProviders.of(this, factory).get(PostsViewModel::class.java)
        viewModel.postLiveData.observe(this, this.dataObserver)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.frag_posts, container, false
        )
        val view = binding.root

        binding.rv.layoutManager = LinearLayoutManager(activity)
        binding.rv.hasFixedSize()
        subscribe = listAdapter.clickEvent.subscribe { model ->
            val extras = FragmentNavigatorExtras(
                model.imageTransition to model.imageTransition.transitionName
            )
            findNavController().navigate(
                PostsFragDirections.actionPostsFragmentToDetailsFragment(
                    DetailFragData(
                        null,
                        model.imageUrl,
                        model.title,
                        model.imageTransition.transitionName,
                        model.titleTransition?.transitionName
                    )
                ),
                extras
            )
        }
        binding.viewModel = viewModel
        binding.postListAdapter = listAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPosts()
    }

    private val dataObserver = Observer<LDR<List<Post>>> { result ->
        when (result?.status) {
            LDR.Status.ERROR -> {
                onError(result.err)
            }
            LDR.Status.SUCCESS -> {
                listAdapter.updateList(result.response!!)
            }
            else -> {
            }
        }
    }

}
