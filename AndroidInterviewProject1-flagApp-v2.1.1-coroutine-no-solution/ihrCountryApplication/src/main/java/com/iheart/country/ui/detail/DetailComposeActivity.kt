package com.iheart.country.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.lifecycleScope
import com.iheart.country.data.Country
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class DetailComposeActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        val view = ComposeView(this).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DetailScreen(viewModel)
            }
        }

        setContentView(view)
    }

    private fun init() {
        viewModel.startActivityEvent.observe(this@DetailComposeActivity, ::onCountryItemClicked)

        intent.getParcelableExtra<Country>(COUNTRY)?.let { country ->
            this@DetailComposeActivity.title = country.name
            viewModel.updateUiState(title = country.name,
                nativeName = country.nativeName,
                languageList = country.languages)
            viewModel.setImage(country.alpha2Code)
            intent.getParcelableArrayListExtra<Country>(REGION_COUNTRIES)
                ?.let {
                    viewModel.setBoundCountries(it, country)
                }
        }

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

    private fun onCountryItemClicked(country: Country?) {
        country.let {
            val intent = Intent(this, DetailComposeActivity::class.java)
            intent.putExtra(COUNTRY, country)
            intent.putExtra(REGION_COUNTRIES, viewModel.filteredCountries)
            startActivity(intent)
        }

    }

}
