package com.yakow.weber.mvisimple.model.repostory

import com.yakow.weber.mvisimple.model.data.models.LanguageModel
import io.reactivex.Observable

/**
 * Created on 29.05.19
 * @author YWeber */

class SearchRepository {

    private fun mockLanguageModel(): List<LanguageModel> {
        val languageList = mutableListOf<LanguageModel>()
        for (i in 0..50) {
            languageList.add(
                LanguageModel(
                    -1,
                    "name",
                    "paradigm $i",
                    "description",
                    "$i $i $i",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1200px-Kotlin-logo.svg.png"
                )
            )
        }
        return languageList.toList()
    }

    fun getLanguageModels(searchLanguage: String): Observable<List<LanguageModel>> = Observable
        .just(mockLanguageModel())
        .map { listResult -> listResult.filter { it.name.toUpperCase() == searchLanguage.toUpperCase() } }
}