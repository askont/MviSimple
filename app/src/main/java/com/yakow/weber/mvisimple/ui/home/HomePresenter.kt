package com.yakow.weber.mvisimple.ui.home

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.yakow.weber.mvisimple.model.interactor.HomeInteractor
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created on 29.05.19
 * @author YWeber */

class HomePresenter(private val interactor: HomeInteractor) :
    MviBasePresenter<HomeView, HomeViewState>() {
    override fun bindIntents() {
        val intent = intent(HomeView::startLoadingContent)
            .switchMap(interactor::getListLanguage)
            .observeOn(AndroidSchedulers.mainThread())
        subscribeViewState(intent, HomeView::render)
    }
}