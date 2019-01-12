package com.wakeel.topmovies.presentation.movieslist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wakeel.topmovies.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.movies_list_fragment.*
import kotlinx.android.synthetic.main.movies_list_fragment.view.*
import javax.inject.Inject

val MOVIES_LIST_FRAGMENT_TAG = MoviesListFragment::class.java.name
private val TAG = MoviesListFragment::class.java.name
fun newMoviesListFragment() = MoviesListFragment()
class MoviesListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var topMoviesAdapter: TopMoviesAdapter
    private var isLoading = false
    private var isLastPage = false

    private val stateObserver = Observer<MoviesListState> { state ->
        state?.let {
            isLastPage = state.loadedAllItems
            when (state) {
                is DefaultState -> {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                    topMoviesAdapter.addMovies(it.data)
                }
                is LoadingState -> {
                    swipeRefreshLayout.isRefreshing = true
                    isLoading = true
                }
                is PaginatingState -> {
                    isLoading = true
                }
                is ErrorState -> {
                    isLoading = false
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesListViewModel::class.java)
        observeViewModel()
        savedInstanceState?.let {
            viewModel.restoreMoviesList()
        } ?: viewModel.updateMoviesList()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.movies_list_fragment, container, false)
        initializeToolbar(view)
        initializeRecyclerView(view)
        initializeSwipeToRefreshView(view)
        return view
    }

    private fun initializeToolbar(view:View) {
        view.toolbar.title = getString(R.string.app_name)
    }

    private fun initializeRecyclerView(view:View) {
        val linearLayoutManager = LinearLayoutManager(context)
        view.recyclerView.apply {
            layoutManager = linearLayoutManager
            topMoviesAdapter = TopMoviesAdapter { movie, view ->
            }
            adapter = topMoviesAdapter
        }
    }

    private fun initializeSwipeToRefreshView(view:View) {
        view.swipeRefreshLayout.setOnRefreshListener { viewModel.resetMoviesList() }
    }
}