package com.yakow.weber.mvisimple.ui.search

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.yakow.weber.mvisimple.model.interactor.SearchInteractor
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created on 27.05.19
 * @author YWeber */

class SearchPresenter(private val interactor: SearchInteractor) :
    MviBasePresenter<SearchView, SearchViewState>() {
    override fun bindIntents() {
        val searchIntent = intent(SearchView::searchIntent)
            .switchMap(interactor::search)
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(searchIntent, SearchView::render)
    }
}