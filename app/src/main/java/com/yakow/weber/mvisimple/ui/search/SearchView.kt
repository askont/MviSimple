package com.yakow.weber.mvisimple.ui.search

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable

/**
 * Created on 27.05.19
 * @author YWeber */

interface SearchView : MvpView {
    fun searchIntent(): Observable<String>
    fun render(searchViewState: SearchViewState)
}