package com.iheart.country.ui.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.iheart.country.R

@Composable
fun  DetailScreen(viewModel: DetailViewModel) {

    val state = viewModel.uiState.collectAsState().value
    DetailScreenLayout(
        viewModel = viewModel,
        state = state
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreenLayout(
    viewModel: DetailViewModel,
    state: DetailViewUiState,
) {
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.fillMaxWidth(),
                title = state.title,
                viewModel = viewModel
            )
        },
        content = {
            Surface(modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
                .fillMaxWidth()
            ) {
                Column {
                    Column(Modifier.verticalScroll(rememberScrollState()).weight(1f).fillMaxWidth()) {
                        FlagCardItem(state = state)
                        BorderList(state = state, viewModel)

                        OfficialLanguageList(state = state)
                    }
                    Text(text = stringResource(id = R.string.back),
                        Modifier.clickable(enabled = true) {
                        viewModel.onBackClicked()
                    }.padding(horizontal = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
    )
}


/**
 * Currently while image is loading there is no placeholder and texts get realigned
 * to avoid that we can use coil library's asyncImage,
 * not using that here, because then setImageFunction will become redundant
 * since we have to pass the link in the asyncImage itself.
 * To avoid realignment, I have wrapped the image inside a box and given a minimum width
 * **/
@Composable
fun FlagCardItem(state: DetailViewUiState) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.defaultMinSize(100.dp)) {

            state.flagImage?.asImageBitmap()
                ?.let {
                    Image(bitmap = it, contentDescription = "Flag",)
                }
        }
        Column(Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)) {
            Text(state.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text("${stringResource(R.string.native_name_title)}\n${state.nativeName}", fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BorderList(state: DetailViewUiState, viewModel: DetailViewModel) {
        Column(Modifier.padding(vertical = 16.dp)) {
            Text("${stringResource(id = R.string.border_section_title, state.boundary.size)}", Modifier.testTag("border"))
            FlowColumn {
                state.boundary.forEach {
                    BorderText(text = it.alpha3Code, viewModel = viewModel)
                }

            }
    }
}


@Composable
fun BorderText(text: String, viewModel: DetailViewModel) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable(enabled = true) {
                viewModel.handleBorderCountrySelected(text)
            },
        text = text

    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OfficialLanguageList(state: DetailViewUiState) {
    Column(Modifier.padding(vertical = 16.dp)) {
        Text("${stringResource(id = R.string.language_section_title)}")
        FlowColumn {
            state.languages.forEach {
                Text(text = "${it.name}:: ${it.nativeName}", Modifier.padding(vertical = 8.dp))
            }

        }
    }
}







@Composable
private fun TopBar(
    modifier: Modifier,
    title: String,
    viewModel: DetailViewModel
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = title, color = MaterialTheme.colors.onSurface) },
        backgroundColor = MaterialTheme.colors.surface,
        navigationIcon = {
            IconButton(onClick = { viewModel.onBackClicked() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
private fun DetailScreenLayoutPreview() {
    DetailScreen(viewModel())
}
