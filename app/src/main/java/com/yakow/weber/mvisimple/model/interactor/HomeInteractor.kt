package com.yakow.weber.mvisimple.model.interactor

import com.yakow.weber.mvisimple.model.repostory.SearchRepository
import com.yakow.weber.mvisimple.ui.home.HomeViewState
import io.reactivex.Observable

/**
 * Created on 30.05.19
 * @author YWeber */

class HomeInteractor(private val searchRepository: SearchRepository) {
    fun getListLanguage(isStart: Boolean): Observable<HomeViewState> =
        if (isStart) {
            searchRepository.getLanguageModels()
                .map { if (it.isEmpty()) HomeViewState.EmptyListResult else HomeViewState.Result(it) }
                .startWith(HomeViewState.Loading)
        } else Observable.just(HomeViewState.EmptyListResult)


}