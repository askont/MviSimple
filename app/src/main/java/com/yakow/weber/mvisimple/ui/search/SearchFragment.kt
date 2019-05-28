package com.yakow.weber.mvisimple.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxbinding2.widget.RxSearchView
import com.yakow.weber.mvisimple.R
import com.yakow.weber.mvisimple.model.interactor.SearchInteractor
import com.yakow.weber.mvisimple.model.repostory.SearchRepository
import com.yakow.weber.mvisimple.ui.adapter.LanguageAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit

/**
 * Created on 28.05.19
 * @author YWeber */

class SearchFragment : MviFragment<SearchView, SearchPresenter>(),
    SearchView {
    override fun createPresenter(): SearchPresenter =
        SearchPresenter(SearchInteractor(SearchRepository()))

    private lateinit var searchAdapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = LanguageAdapter(listOf())
        searchAdapter.addClickListener {
            Toast.makeText(requireContext(), "test test", Toast.LENGTH_SHORT).show()
        }
        languageRecyclerView.adapter = searchAdapter

    }

    override fun render(searchViewState: SearchViewState) {
        when (searchViewState) {
            is SearchViewState.Loading -> {
                Toast.makeText(requireContext(), "загрузка", Toast.LENGTH_SHORT).show()
            }
            is SearchViewState.SearchNotStartedYet -> {
                Toast.makeText(requireContext(), "start", Toast.LENGTH_SHORT).show()
            }
            is SearchViewState.EmptyResult -> {
                searchAdapter.refreshData(listOf())
            }
            is SearchViewState.Error -> {
                Toast.makeText(
                    requireContext(),
                    "ошибка ${searchViewState.searchQueryText}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is SearchViewState.SearchResult -> {
                searchAdapter.refreshData(searchViewState.result)
            }

        }
    }

    override fun searchIntent(): Observable<String> = RxSearchView.queryTextChanges(searchView)
        .skipInitialValue()
        .map { it.toString() }
        .filter { (it.length > 3) || it.isEmpty() }
        .debounce(500, TimeUnit.MILLISECONDS)

}