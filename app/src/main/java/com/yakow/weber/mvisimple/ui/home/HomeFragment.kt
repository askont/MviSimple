package com.yakow.weber.mvisimple.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hannesdorfmann.mosby3.mvi.MviFragment
import com.jakewharton.rxbinding2.support.design.widget.RxSwipeDismissBehavior
import com.yakow.weber.mvisimple.R
import com.yakow.weber.mvisimple.model.interactor.HomeInteractor
import com.yakow.weber.mvisimple.model.repostory.SearchRepository
import com.yakow.weber.mvisimple.ui.adapter.LanguageAdapter
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created on 29.05.19
 * @author YWeber */

class HomeFragment : MviFragment<HomeView, HomePresenter>(), HomeView,
    SwipeRefreshLayout.OnRefreshListener {
    override fun newLoadingContent(): Observable<Boolean> =
        RxSwipeDismissBehavior.dismisses(homeSwipeRefresh).map { true }

    override fun onRefresh() {
        homeSwipeRefresh.isRefreshing = false
    }

    private lateinit var homeAdapter: LanguageAdapter

    override fun createPresenter(): HomePresenter =
        HomePresenter(HomeInteractor(SearchRepository()))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeSwipeRefresh.setOnRefreshListener(this)
        homeAdapter = LanguageAdapter(listOf())
        homeAdapter.addClickListener {
            Toast.makeText(requireContext(), "в разработке", Toast.LENGTH_SHORT).show()
        }
        homeRecyclerView.adapter = homeAdapter
    }

    override fun startLoadingFirstContent(): Observable<Boolean> = Observable.just(true)

    override fun render(viewState: HomeViewState) {
        when (viewState) {
            is HomeViewState.EmptyListResult -> {
                Toast.makeText(requireContext(), "в разработке", Toast.LENGTH_SHORT).show()
            }
            is HomeViewState.Loading -> {
                Toast.makeText(requireContext(), "в процессе", Toast.LENGTH_SHORT).show()
            }
            is HomeViewState.Result -> {
                homeAdapter.refreshData(viewState.listLanguage)
            }
        }
    }

}