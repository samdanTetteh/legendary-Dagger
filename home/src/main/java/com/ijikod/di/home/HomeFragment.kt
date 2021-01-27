package com.ijikod.di.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ijikod.di.di.viewmodel.AppViewModelFactory
import com.ijikod.di.home.databinding.ScreenHomeBinding
import com.ijikod.di.home.list.HomeRepoAdapter
import com.ijikod.poweradapter.ItemRenderer
import com.ijikod.poweradapter.PowerAdapter
import com.ijikod.poweradapter.PowerAdapterViewHolder
import com.ijikod.poweradapter.RecyclerItem
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : Fragment() {

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    @Inject
    lateinit var itemRenderers: Map<Class<out RecyclerItem>, @JvmSuppressWildcards Provider<ItemRenderer<out RecyclerItem>>>

    private val homeViewModule: HomeViewModel by lazy {
        ViewModelProvider(this, appViewModelFactory)[HomeViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ScreenHomeBinding.inflate(inflater, container, false)

        setUpRecyclerView(binding)

        observerState(binding)

        return binding.root
    }


    private fun setUpRecyclerView(binding: ScreenHomeBinding) {
        binding.repoList.apply {
            adapter = PowerAdapter(itemRenderers)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }


    private fun observerState(binding: ScreenHomeBinding) {
        homeViewModule.viewStateUpdates.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is HomeViewStateLoading -> handleLoadingState(binding)
                is HomeViewSateLoaded -> handleLoadedState(state, binding)
                is HomeViewStateError -> handleErrorState(state, binding)
            }
        })
    }

    private fun handleErrorState(state: HomeViewStateError, binding: ScreenHomeBinding) {
        binding.loadingIndicator.visibility = View.GONE
        binding.repoList.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE

        binding.errorTextView.text = state.message
    }

    private fun handleLoadedState(state: HomeViewSateLoaded, binding: ScreenHomeBinding) {
        binding.loadingIndicator.visibility = View.GONE
        binding.repoList.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE

        (binding.repoList.adapter as PowerAdapter).setData(state.repos)
    }

    private fun handleLoadingState(binding: ScreenHomeBinding) {
        binding.loadingIndicator.visibility = View.VISIBLE
        binding.repoList.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE
    }




}