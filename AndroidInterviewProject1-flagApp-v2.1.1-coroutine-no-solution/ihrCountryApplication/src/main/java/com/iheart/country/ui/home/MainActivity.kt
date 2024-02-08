package com.iheart.country.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.iheart.country.data.Country
import com.iheart.country.databinding.ActivityMainBinding
import com.iheart.country.ui.base.BaseActivity
import com.iheart.country.ui.detail.COUNTRY
import com.iheart.country.ui.detail.DetailComposeActivity
import com.iheart.country.ui.detail.REGION_COUNTRIES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel: MainViewModel by viewModels()

    override fun initViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            countriesLiveData.observe(this@MainActivity, ::onCountriesListLoaded)
        }
    }



    private fun onCountriesListLoaded(data: List<Country>) {
        with(viewBinding) {
            progress.visibility = View.GONE
            if (data.isEmpty()) {
                nullTextView.visibility = View.VISIBLE
            }
            else {
                gridview.visibility = View.VISIBLE
                val countryDridviewAdapter = CountryGridviewAdapter(this@MainActivity, data)
                gridview.adapter = countryDridviewAdapter
                gridview.setOnItemClickListener { adapterView, view, i, l ->
                    onItemClicked(data[i])
                }
            }

        }
    }

    private fun onItemClicked(country: Country) {
        val intent = Intent(this, DetailComposeActivity::class.java)
        intent.putExtra(COUNTRY, country)
        intent.putExtra(REGION_COUNTRIES, viewModel.getFilteredCountries(country))
        startActivity(intent)

    }



}
