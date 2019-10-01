package com.amin.sample.ui.imgur


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amin.sample.R
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.databinding.FragImgurBinding
import com.amin.sample.utils.LDR
import com.amin.sample.utils.extensions.showDismissDialog
import com.amin.sample.utils.view.ItemDecoration


class ImgurListFrag : Fragment() {

    val args by navArgs<ImgurListFragArgs>()
    var sort = "viral"
    private lateinit var binding: FragImgurBinding
    private lateinit var viewModel: ImgurViewModel
    private val listAdapter = ImgurAdapter()
    var clickEvent = listAdapter.clickEvent
    private var page = 1
    private var isLoadMore = false
    private var isFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val factory = ImgurViewModelFactory()

        viewModel = ViewModelProviders.of(this, factory).get(ImgurViewModel::class.java)
        viewModel.apiLiveData.observe(this, this.dataObserver)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.frag_imgur, container, false
        )
        val view = binding.root
        binding.rv.layoutManager = GridLayoutManager(activity, 3)
        binding.rv.hasFixedSize()
        binding.rv.addItemDecoration(ItemDecoration())
        binding.viewModel = viewModel
        binding.listAdapter = listAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadImages(1, args.sectionType, sort)
    }

    private val dataObserver = Observer<LDR<BaseResponseShutterStock>> { result ->
        when (result?.status) {
            LDR.Status.ERROR -> {
                showDismissDialog(result.err!!.localizedMessage!!)
            }

            LDR.Status.SUCCESS -> {
                isLoadMore = false
                listAdapter.updateList(result.response!!.data)
            }

            else -> {
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.rv.removeOnScrollListener(onScrollChangeListener)
    }

    override fun onResume() {
        super.onResume()
        binding.rv.addOnScrollListener(onScrollChangeListener)
    }

    private val onScrollChangeListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layManager = binding.rv.layoutManager!! as GridLayoutManager
            val visibleItemCount = layManager.childCount
            val totalItemCount = layManager.itemCount
            val firstVisibleItemPosition = layManager.findFirstVisibleItemPosition()
            if (!isLoadMore && !isFinished) {
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                    && firstVisibleItemPosition >= 0
                ) {
                    page++
                    isLoadMore = true
                    viewModel.loadImages(page, args.sectionType, sort)
                }
            }
        }
    }

    companion object {
        fun newInstance(bundle: ImgurListFragArgs) =
            ImgurListFrag().apply {
                arguments = bundle.toBundle()
            }
    }
}
