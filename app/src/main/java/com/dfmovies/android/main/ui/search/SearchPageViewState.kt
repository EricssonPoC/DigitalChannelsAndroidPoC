package com.dfmovies.android.main.ui.search

data class SearchPageViewState(val searchText: String? = null) {

    fun isClearSearchButtonVisible():Boolean{
        return searchText.isNullOrEmpty().not()
    }
}