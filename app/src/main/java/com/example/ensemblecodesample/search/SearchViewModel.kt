package com.example.ensemblecodesample.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.ensemblecodesample.core.data.Item
import com.example.ensemblecodesample.core.data.OpenDataBaseMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: OpenDataBaseMovieRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    var searchText: StateFlow<String> = _searchText

    private val _itemsPagingData = MutableStateFlow<PagingData<Item>>(PagingData.empty())
    val itemsPagingData: StateFlow<PagingData<Item>> get() = _itemsPagingData

    init {
        viewModelScope.launch {
            searchText
                .debounce(100L)
                .filter { it.length > 3 }
                .collect {
                    searchItem()
                }
        }
    }

    private fun searchItem() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(pageSize = 10, prefetchDistance = 2),
                pagingSourceFactory = {
                    ItemsSource(repository, _searchText.value)
                }
            ).flow
                .distinctUntilChanged()
                .cachedIn(scope = viewModelScope)
                .collect{
                    _itemsPagingData.value = it
                }
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        if (text.isEmpty())
            _itemsPagingData.value = PagingData.empty()
    }

    fun clearData() {
        _searchText.value = ""
        _itemsPagingData.value = PagingData.empty()
    }
}