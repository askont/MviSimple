package com.yakow.weber.mvisimple.ui.home

import com.yakow.weber.mvisimple.model.data.models.LanguageModel

/**
 * Created on 30.05.19
 * @author YWeber */

sealed class HomeViewState {
    object EmtyListResult : HomeViewState()
    object Loading: HomeViewState()
    data class Result(val listLanguage: List<LanguageModel>): HomeViewState()
}