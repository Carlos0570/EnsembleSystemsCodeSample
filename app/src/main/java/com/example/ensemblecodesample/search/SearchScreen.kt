package com.example.ensemblecodesample.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.ensemblecodesample.core.data.Item
import com.example.ensemblecodesample.R

@Composable
fun SearchScreen(searchViewModel: SearchViewModel) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SearchTextField(searchViewModel, focusRequester)
        SearchResults(searchViewModel)
    }
}

@Composable
private fun SearchTextField(
    searchViewModel: SearchViewModel,
    focusRequester: FocusRequester
) {
    val searchText by searchViewModel.searchText.collectAsState()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                BasicTextField(
                    value = TextFieldValue(
                        text = searchText,
                        selection = TextRange(searchText.length)
                    ),
                    onValueChange = { searchViewModel.onSearchTextChange(it.text) },
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp, end = 8.dp)
                        .focusRequester(focusRequester)
                )
                Box(
                    Modifier
                        .padding(vertical = 6.dp)
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.tertiary.copy(alpha = .5f))
                )
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier
                        .padding(9.dp)
                        .clickable { searchViewModel.clearData() }
                )
            }
        }
    }
}

@Composable
private fun SearchResults(searchViewModel: SearchViewModel) {
    val lazyPagingItems = searchViewModel.itemsPagingData.collectAsLazyPagingItems()
    LazyColumn {
        items(lazyPagingItems.itemCount) { pos ->
            lazyPagingItems[pos]?.let {
                ItemCard(item = it)
            }
        }
    }
}

@Composable
private fun ItemCard(item: Item) {
    Row {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(MaterialTheme.shapes.small)
        ) {
            AsyncImage(
                model = item.poster,
                placeholder = null,
                error =  null,
                contentDescription = "Item poster",
                modifier = Modifier
                    .height(110.dp)
                    .width(160.dp),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier.width(230.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            item.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            item.year?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier.size(width = 110.dp, height = 30.dp),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.do_nothing),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}