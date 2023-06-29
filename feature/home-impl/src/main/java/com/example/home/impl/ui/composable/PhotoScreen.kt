package com.example.home.impl.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home.impl.vm.PhotosViewModel
import com.example.mytruth.feature.home.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreen(modifier: Modifier = Modifier, viewModel: PhotosViewModel = hiltViewModel()) {
    val photos = viewModel.state.photosList

    val listState = rememberLazyListState()
    LaunchedEffect(Unit) {
        viewModel.getPhotosWithStaleCheck()
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = Icons.Default.Add.name,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.truth)) },
                navigationIcon = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = Icons.Filled.Menu.name)
                    }
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Search, contentDescription = Icons.Filled.Search.name)
                    }
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = stringResource(R.string.localized_description)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(state = listState, contentPadding = paddingValues) {
            items(photos) { photo ->
                PhotoItem(photo = photo, listState = listState)
            }
        }
    }
}
