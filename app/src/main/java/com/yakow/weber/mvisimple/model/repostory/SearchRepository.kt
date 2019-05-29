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
                    "Kotlin",
                    " статически типизированный язык программирования",
                    "Авторы ставили целью создать язык более лаконичный и типобезопасный, чем Java, и более простой, чем Scala[3]. Следствием упрощения по сравнению со Scala стали также более быстрая компиляция и лучшая поддержка языка в IDE[4]. Язык полностью совместим с Java, что позволяет java-разработчикам постепенно перейти к его использованию; в частности, в Android язык встраивается с помощью Gradle, что позволяет для существующего android-приложения внедрять новые функции на Kotlin без переписывания приложения целиком.",
                    "с 2010 года",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1200px-Kotlin-logo.svg.png"
                )
            )
        }
        return languageList.toList()
    }

    fun getLanguageModels(searchLanguage: String = ""): Observable<List<LanguageModel>> = Observable
        .just(mockLanguageModel())
}