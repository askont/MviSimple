package com.yakow.weber.mvisimple.ui.search

import com.yakow.weber.mvisimple.model.data.models.LanguageModel

/**
 * Created on 27.05.19
 * @author YWeber */

sealed class SearchViewState {
    object Loading : SearchViewState()
    object SearchNotStartedYet : SearchViewState()

    data class EmptyResult(val searchQueryText: String) : SearchViewState()

    data class SearchResult(
        val searchQueryText: String,
        val result: List<LanguageModel>
    ) : SearchViewState()

    data class Error(
        val searchQueryText: String,
        val error: Throwable
    ) : SearchViewState()

}