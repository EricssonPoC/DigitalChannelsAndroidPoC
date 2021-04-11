package com.dfmovies.android.main.ui.search

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.dfmovies.android.R
import androidx.core.widget.addTextChangedListener
import com.dfmovies.android.core.ui.BaseFragment
import com.dfmovies.android.core.util.extension.applySharedAxisYTransition
import com.dfmovies.android.core.util.extension.hideKeyboard
import com.dfmovies.android.core.util.extension.observeNonNull
import com.dfmovies.android.core.util.extension.showKeyboardWithFocus
import com.dfmovies.android.core.util.recyclerview.SimpleDividerItemDecoration
import com.dfmovies.android.core.util.widget.DynamicToolbarViewState
import com.dfmovies.android.databinding.FragmentSearchBinding
import com.dfmovies.android.main.ui.adapter.SearchMoviesPagingAdapter
import com.dfmovies.android.main.ui.detail.MovieDetailFragment
import com.dfmovies.android.main.ui.detail.MovieDetailFragmentArgument
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchFragmentViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getActivityViewModelProvider().get(SearchFragmentViewModel::class.java)
    }

    @Inject
    lateinit var searchAdapter: SearchMoviesPagingAdapter

    override fun getLayoutId(): Int = R.layout.fragment_search

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

            getPageViewStateLiveData().observeNonNull(viewLifecycleOwner) { statusViewState ->
                setPageViewState(statusViewState)
            }

            getMoviesLiveData().observeNonNull(viewLifecycleOwner) { data ->
                lifecycleScope.launch {
                    searchAdapter.submitData(data)
                }
            }

            getErrorMessageLiveData().observeNonNull(viewLifecycleOwner) { error ->
                showShortToastMessage(error.message)
            }

            loadInitialData()
        }
    }

    private fun setUpView() {
        with(binding) {

            editTextSearch.addTextChangedListener(
                afterTextChanged = { editable ->
                    updateSearchQueryInViewState(searchQuery = editable.toString())
                    viewModel.setSearchQuery(editable.toString())
                    searchAdapter.refresh()
                }
            )

            imageViewBackButton.setOnClickListener {
                super.onBackPressed()
            }

            imageClearSearchQuery.setOnClickListener {
                editTextSearch.setText("")
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

                adapter = searchAdapter
            }

            searchAdapter.itemClickListener = { movie ->
                startFragment(
                    MovieDetailFragment.newInstance(
                        MovieDetailFragmentArgument(
                            movie = movie
                        )
                    ).also {
                        editTextSearch.hideKeyboard()
                    }
                )
            }

            searchAdapter.addLoadStateListener { state ->
                viewModel.setPageState(state)
            }

            editTextSearch.showKeyboardWithFocus()

        }
    }

    private fun setPageStatusViewState(statusViewState: SearchPageStatusViewState) {
        binding.viewStateStatus = statusViewState
        binding.executePendingBindings()
    }

    private fun setPageViewState(viewState: SearchPageViewState) {
        binding.viewState = viewState
        binding.executePendingBindings()
    }

    private fun updateSearchQueryInViewState(searchQuery: String) {
        binding.viewState?.let { viewState ->
            setPageViewState(viewState.copy(searchText = searchQuery))
        }
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}