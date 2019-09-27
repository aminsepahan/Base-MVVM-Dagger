package com.amin.sample.ui.shutterStock


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.amin.sample.R
import com.amin.sample.base.BaseResponseShutterStock
import com.amin.sample.databinding.FragShutterStockBinding
import com.amin.sample.utils.LDR


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
        //here data must be an instance of the class MarsDataProvider
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
                // Loading data
            }

            LDR.Status.ERROR -> {

            }

            LDR.Status.SUCCESS -> {
                listAdapter.updateList(result.response!!.data)
            }

            else ->{}
        }
    }

}
