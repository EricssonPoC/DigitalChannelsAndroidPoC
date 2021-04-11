package com.dfmovies.android.main.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.dfmovies.android.R
import com.dfmovies.android.core.ui.BaseFragment
import com.dfmovies.android.core.util.extension.applySharedAxisXTransition
import com.dfmovies.android.core.util.extension.applySharedAxisYTransition
import com.dfmovies.android.core.util.extension.observeNonNull
import com.dfmovies.android.core.util.widget.DynamicToolbarViewState
import com.dfmovies.android.databinding.FragmentMovieDetailBinding
import com.dfmovies.android.main.ui.MainActivity
import com.dfmovies.android.main.ui.search.SearchFragmentViewModel
import com.dfmovies.android.main.ui.search.SearchPageStatusViewState
import com.dfmovies.android.main.ui.search.SearchPageViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getActivityViewModelProvider().get(MovieDetailViewModel::class.java)
    }

    @Inject
    lateinit var detailArgument: MovieDetailFragmentArgument

    override fun getLayoutId(): Int = R.layout.fragment_movie_detail

    override fun getScreeningPage(): String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applySharedAxisXTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBottomNavigation()
        setUpView()
        setUpViewModel()
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

            initData(detailArgument.movie)
        }
    }

    private fun setUpView() {
        with(binding) {

            toolbar.setViewState(
                    DynamicToolbarViewState(
                            title = detailArgument.movie.title,
                            leftIconVisibility = true

                    )
            )

            toolbar.backButtonClickListener = {
                super.onBackPressed()
            }

            imageViewFavorite.setOnClickListener {
                viewModel.createOrUpdateForFavorite()
            }

            imageViewWatchlist.setOnClickListener {
                viewModel.createOrUpdateForWatchlist()
            }

        }
    }

    private fun setPageViewState(viewState: MovieDetailPageViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    private fun setPageStatusViewState(statusViewState: MovieDetailPageStatusViewState) {
        binding.viewStateStatus = statusViewState
        binding.executePendingBindings()
    }


    companion object {
        const val MOVIE_ARGS = "MOVIE_ARGS"

        fun newInstance(movieArgument: MovieDetailFragmentArgument): MovieDetailFragment {
            return MovieDetailFragment().apply {
                arguments = bundleOf(
                        MOVIE_ARGS to movieArgument
                )
            }
        }
    }
}