package com.amin.sample.ui.shutterStock


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amin.sample.R
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.databinding.FragShutterStockBinding
import com.amin.sample.utils.LDR
import com.amin.sample.utils.extensions.showDismissDialog
import com.amin.sample.utils.view.ItemDecoration
import kotlinx.coroutines.*


class ShutterStockListFrag : Fragment() {

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
        viewModel.loadImages()
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
        page = 1
        listAdapter.clear()
        viewModel.searchPhrase = phrase
        viewModel.loadImages(page)
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
                    viewModel.loadImages(page)
                }
            }
        }
    }

}
