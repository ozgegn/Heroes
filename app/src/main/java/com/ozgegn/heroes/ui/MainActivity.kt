package com.ozgegn.heroes.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozgegn.heroes.R
import com.ozgegn.heroes.base.DataBindingActivity
import com.ozgegn.heroes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModels<MainViewModel>()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(binding.mainToolbar)

        binding.apply {
            lifecycleOwner = this@MainActivity
            adapter = AdapterHeroes()
            heroesList.layoutManager = LinearLayoutManager(this@MainActivity)
            vm = viewModel.apply { fetchHeroes(0) }
        }

        subscribeData()
    }

    private fun subscribeData(){
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrBlank() && query.length > 2) {
            viewModel.searchHeroes(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrBlank() && newText.length > 2) {
            viewModel.searchHeroes(newText)
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.adapter?.clearAdapter()
                viewModel.fetchHeroes(0)
                return true
            }
        }
        menuItem.setOnActionExpandListener(expandListener)
        searchView.apply {
            queryHint = getString(R.string.menu_search_hilt)
            setOnQueryTextListener(this@MainActivity)
        }
        return super.onCreateOptionsMenu(menu)
    }
}