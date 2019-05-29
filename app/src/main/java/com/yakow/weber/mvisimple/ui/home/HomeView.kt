package com.yakow.weber.mvisimple.ui.home

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/**
 * Created on 29.05.19
 * @author YWeber */

interface HomeView : MvpView {
    fun startLoadingContent(): Observable<Boolean>
    fun render(viewState: HomeViewState)
}