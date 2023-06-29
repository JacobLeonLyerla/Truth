package com.example.home.impl.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.home.impl.model.local.entity.Photo
import com.example.mytruth.feature.home.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PhotoItem(photo: Photo, listState: LazyListState, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    ) {
        Image(
            painter = rememberAsyncImagePainter(photo.url),
            contentDescription = photo.title,
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = photo.title,
                style = MaterialTheme.typography.titleLarge
            )
            FlowRow(modifier = Modifier.fillMaxWidth()) {
                AssistChip(
                    onClick = {
                        coroutineScope.launch {
                            listState.scrollToItem(0)
                        }
                    },
                    label = { Text(stringResource(R.string.top)) },
                    colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowUp,
                            contentDescription = null
                        )
                    }
                )
                AssistChip(
                    onClick = {
                        coroutineScope.launch {
                            listState.scrollToItem(listState.layoutInfo.totalItemsCount - 1)
                        }
                    },
                    label = { Text(stringResource(R.string.bottom)) },
                    colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                )
                AssistChip(
                    onClick = {},
                    label = { Text(stringResource(R.string.add_to_board)) },
                    colors = AssistChipDefaults.assistChipColors(leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.AddCircle,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}
