package com.dfmovies.android.main.ui.discover

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.dfmovies.android.R
import com.dfmovies.android.core.ui.BaseFragment
import com.dfmovies.android.core.util.extension.observeNonNull
import com.dfmovies.android.core.util.recyclerview.SimpleDividerItemDecoration
import com.dfmovies.android.core.util.widget.DynamicToolbarViewState
import com.dfmovies.android.databinding.FragmentDiscoverBinding
import com.dfmovies.android.main.ui.adapter.SearchMoviesPagingAdapter
import com.dfmovies.android.main.ui.detail.MovieDetailFragment
import com.dfmovies.android.main.ui.detail.MovieDetailFragmentArgument
import com.dfmovies.android.main.ui.search.SearchFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class DiscoverMovieFragment : BaseFragment<FragmentDiscoverBinding>() {

    private val viewModel: DiscoverMovieFragmentViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getActivityViewModelProvider().get(DiscoverMovieFragmentViewModel::class.java)
    }

    @Inject
    lateinit var discoverAdapter: SearchMoviesPagingAdapter

    override fun getLayoutId(): Int = R.layout.fragment_discover

    override fun getScreeningPage(): String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden.not()) {
            showBottomNavigation()
        }
    }

    private fun setUpViewModel() {
        with(viewModel) {
            getPageStatusViewStateLiveData().observeNonNull(viewLifecycleOwner) { statusViewState ->
                setPageStatusViewState(statusViewState)
            }

            getMoviesLiveData().observeNonNull(viewLifecycleOwner) { data ->
                lifecycleScope.launch {
                    discoverAdapter.submitData(data)
                }
            }

            getErrorMessageLiveData().observeNonNull(viewLifecycleOwner) { error ->
                showShortToastMessage(error.message)
            }

            loadMovies()
        }
    }

    private fun setPageStatusViewState(statusViewState: DiscoverMoviePageStatusViewState) {
        binding.viewStateStatus = statusViewState
        binding.executePendingBindings()
    }

    private fun setUpView() {
        with(binding) {
            toolbar.setViewState(
                    DynamicToolbarViewState(
                            title = getString(R.string.discover_page_title)
                    )
            )

            textViewSearch.setOnClickListener {
                startFragment(SearchFragment.newInstance())
            }

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

                adapter = discoverAdapter
            }

            discoverAdapter.itemClickListener = { movie ->
                startFragment(
                        MovieDetailFragment.newInstance(
                                MovieDetailFragmentArgument(
                                        movie = movie
                                )
                        )
                )
            }

            discoverAdapter.addLoadStateListener { state ->
                viewModel.setPageState(state)
            }

        }
    }

    companion object {
        fun newInstance(): DiscoverMovieFragment {
            return DiscoverMovieFragment()
        }
    }
}