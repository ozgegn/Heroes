package com.ozgegn.heroes.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ozgegn.heroes.base.RecyclerViewPaginator
import com.ozgegn.heroes.data.local.HeroEntity
import com.ozgegn.heroes.ui.AdapterHeroes
import com.ozgegn.heroes.ui.MainViewModel

@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("heroList")
fun bindHeroList(view: RecyclerView, list: List<HeroEntity>?) {
    list?.let {
        (view.adapter as? AdapterHeroes)?.addHeroes(it)
    }
}

@BindingAdapter("filteredHeroList")
fun bindFilteredHeroList(view: RecyclerView, list: List<HeroEntity>?) {
    list?.let {
        (view.adapter as? AdapterHeroes)?.addHeroListAfterReset(it)
    }
}

@BindingAdapter("paging")
fun bindPaginationAdapter(view: RecyclerView, viewModel: MainViewModel) {
    RecyclerViewPaginator(
        recyclerView = view,
        isLoading = { viewModel.isLoading().get() },
        loadMore = { viewModel.fetchHeroes(it) },
        onLast = { false }
    )
}