package com.amin.sample.ui.shutterStock


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amin.sample.R
import com.amin.sample.base.BaseFragment
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.databinding.FragShutterStockBinding
import com.amin.sample.model.DetailFragData
import com.amin.sample.utils.LDR
import com.amin.sample.utils.extensions.showDismissDialog
import com.amin.sample.utils.view.ItemDecoration
import kotlinx.coroutines.*


class ShutterStockListFrag : BaseFragment() {

    private lateinit var textChangeCountDownJob: Job
    private lateinit var binding: FragShutterStockBinding
    private lateinit var viewModel: ShutterStockViewModel
    private val listAdapter = ShutterStockAdapter()
    private var page = 1
    private var isLoadMore = false
    private var isFinished = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = ShutterStockViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(ShutterStockViewModel::class.java)
        viewModel.apiLiveData.observe(this, this.dataObserver)
        binding = DataBindingUtil.inflate(
            inflater, R.layout.frag_shutter_stock, container, false
        )
        binding.rv.layoutManager = GridLayoutManager(activity, 2)
        binding.rv.hasFixedSize()
        binding.rv.addItemDecoration(ItemDecoration(3))
        binding.viewModel = viewModel
        binding.listAdapter = listAdapter
        subscribe = listAdapter.clickEvent.subscribe { model ->
            val extras = FragmentNavigatorExtras(
                model.imageTransition to model.imageTransition.transitionName
            )
            findNavController().navigate(
                ShutterStockListFragDirections.actionShutterStockListFragToDetailsFragment(
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (listAdapter.isEmpty()) {
            viewModel.loadImages()
        }
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (::textChangeCountDownJob.isInitialized)
                    textChangeCountDownJob.cancel()

                textChangeCountDownJob = GlobalScope.launch(Dispatchers.Main) {
                    delay(700)
                    search(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun search(phrase: String) {
        if (viewModel.searchPhrase != phrase) {
            page = 1
            listAdapter.clear()
            viewModel.searchPhrase = phrase
            viewModel.loadImages(page)
        }
    }

    private val dataObserver = Observer<LDR<BaseResponseShutterStock>> { response ->
        when (response?.status) {
            LDR.Status.ERROR -> {
                onError(response.err)
            }

            LDR.Status.SUCCESS -> {
                isLoadMore = false
                listAdapter.updateList(response.response!!.data)
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
                    viewModel.loadImages(page)
                }
            }
        }
    }

}
