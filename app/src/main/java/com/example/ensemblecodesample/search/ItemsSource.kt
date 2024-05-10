package com.example.ensemblecodesample.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ensemblecodesample.core.data.Item
import com.example.ensemblecodesample.core.data.OpenDataBaseMovieRepository

class ItemsSource(private val openDataBaseMovieRepository: OpenDataBaseMovieRepository, private val title: String) : PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>) = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val currentPage = params.key ?: 1
        val result = openDataBaseMovieRepository.searchItem(title, currentPage)
        return LoadResult.Page(
            data = result.items,
            prevKey = if (currentPage == 1) null else currentPage - 1,
            nextKey = if (result.items.isEmpty()) null else currentPage + 1
        )
    }
}