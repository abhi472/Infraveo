package com.iheart.country.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.iheart.country.databinding.DetailViewBinding
import com.iheart.country.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<DetailViewModel, DetailViewBinding>() {

    override val viewModel: DetailViewModel by viewModels()

    override fun initViewBinding(): DetailViewBinding = DetailViewBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initView()

    }
    private fun initView() {
        setContentView(viewBinding.root)

        with(viewBinding) {
            title.text = viewModel.uiState.value.title
        }
    }

    private fun init() {


        lifecycleScope.launchWhenStarted {
            viewModel.navigationEvents.collect { navigationEvent ->
                when (navigationEvent) {
                    NavigationEvent.NavigateBack -> {
                        onBackPressed()
                    }
                }
            }
        }
    }



}
