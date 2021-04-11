package com.dfmovies.android.main.ui.watchlist

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.dfmovies.android.R
import com.dfmovies.android.core.ui.BaseFragment
import com.dfmovies.android.core.util.extension.observeNonNull
import com.dfmovies.android.core.util.recyclerview.SimpleDividerItemDecoration
import com.dfmovies.android.core.util.widget.DynamicToolbarViewState
import com.dfmovies.android.databinding.FragmentWathclistBinding
import com.dfmovies.android.main.ui.MainActivity
import com.dfmovies.android.main.ui.MainTabs
import com.dfmovies.android.main.ui.adapter.MoviesAdapter
import com.dfmovies.android.main.ui.detail.MovieDetailFragment
import com.dfmovies.android.main.ui.detail.MovieDetailFragmentArgument
import javax.inject.Inject

class WatchlistFragment : BaseFragment<FragmentWathclistBinding>() {

    private val viewModel: WatchlistFragmentViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getActivityViewModelProvider().get(WatchlistFragmentViewModel::class.java)
    }

    @Inject
    lateinit var watchlistAdapter: MoviesAdapter

    override fun getLayoutId(): Int = R.layout.fragment_wathclist

    override fun getScreeningPage(): String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden.not()) {
            showBottomNavigation()
            getWatchlistMovies()
        }
    }

    override fun onResume() {
        super.onResume()
        getWatchlistMovies()
    }

    private fun setUpViewModel() {
        with(viewModel) {
            getPageStatusViewStateLiveData().observeNonNull(viewLifecycleOwner) { statusViewState ->
                setPageStatusViewState(statusViewState)
            }

            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) { viewState ->
                setPageViewState(viewState)
            }

            getErrorMessageLiveData().observeNonNull(viewLifecycleOwner) { error ->
                showShortToastMessage(error.message)
            }
        }
    }

    private fun getWatchlistMovies() {
        viewModel.getWatchlistMovies()
    }

    private fun setUpView() {
        with(binding) {
            toolbar.setViewState(
                    DynamicToolbarViewState(
                            title = getString(R.string.watchlist_page_title)
                    )
            )

            recyclerViewItems.apply {
                addItemDecoration(
                        SimpleDividerItemDecoration(
                                context = context,
                                orientation = DividerItemDecoration.VERTICAL,
                                skipLastItem = true
                        ).also { itemDecoration ->
                            ContextCompat.getDrawable(context, R.drawable.shape_divider)
                                    ?.let { divider ->
                                        itemDecoration.setDrawable(divider)
                                    }
                        }
                )

                adapter = watchlistAdapter
            }

            watchlistAdapter.itemClickListener = { movie ->
                startFragment(
                        MovieDetailFragment.newInstance(
                                MovieDetailFragmentArgument(
                                        movie = movie
                                )
                        )
                )
            }

            stateLayout.infoButtonListener {
                (activity as? MainActivity)?.switchTab(MainTabs.DISCOVER.index)
            }
        }
    }

    private fun setPageViewState(viewState: WatchlistPageViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    private fun setPageStatusViewState(statusViewState: WatchlistPageStatusViewState) {
        binding.viewStateStatus = statusViewState
        binding.executePendingBindings()
    }

    companion object {
        fun newInstance(): WatchlistFragment {
            return WatchlistFragment()
        }
    }
}