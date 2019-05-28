package com.yakow.weber.mvisimple.model.interactor

import com.yakow.weber.mvisimple.model.repostory.SearchRepository
import com.yakow.weber.mvisimple.ui.search.SearchViewState
import io.reactivex.Observable

/**
 * Created on 27.05.19
 * @author YWeber */

class SearchInteractor(private val repository: SearchRepository) {

    fun search(searchText: String): Observable<out SearchViewState> =
        if (searchText.isEmpty()) Observable.just(SearchViewState.SearchNotStartedYet)
        else repository.getLanguageModels(searchText)
            .map { searchList ->
                if (searchList.isEmpty()) {
                    SearchViewState.EmptyResult(searchText)
                } else {
                    SearchViewState.SearchResult(searchText, searchList)
                }
            }
            .startWith(SearchViewState.Loading)
            .onErrorReturn { error -> SearchViewState.Error(searchText, error) }
}