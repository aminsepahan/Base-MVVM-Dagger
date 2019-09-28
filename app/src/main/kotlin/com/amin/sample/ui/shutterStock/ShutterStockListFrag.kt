package com.amin.sample.ui.shutterStock


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amin.sample.R
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.databinding.FragShutterStockBinding
import com.amin.sample.utils.LDR
import com.amin.sample.utils.extensions.showDismissDialog


class ShutterStockListFrag : Fragment() {

    private lateinit var binding: FragShutterStockBinding
    private lateinit var viewModel: ShutterStockViewModel
    val listAdapter: ShutterStockAdapter = ShutterStockAdapter()

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
//        binding.rv.layoutManager = GridLayoutManager(activity, 2)
//        binding.rv.hasFixedSize()
        binding.viewModel = viewModel
        binding.listAdapter = listAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadImages()
    }
    private val dataObserver = Observer<LDR<BaseResponseShutterStock>> { result ->
        when (result?.status) {
            LDR.Status.LOADING -> {
            }

            LDR.Status.ERROR -> {
                showDismissDialog(result.err!!.localizedMessage!!)
            }

            LDR.Status.SUCCESS -> {
                listAdapter.updateList(result.response!!.data)
            }

            else ->{}
        }
    }

}
