package com.example.kinopoisk.movie.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentMovieListBinding
import com.example.kinopoisk.movie.list.adapters.MovieListRvAdapter
import com.example.kinopoisk.movie.list.adapters.MovieListErrorAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val viewModel: MovieListViewModel by viewModels()
    private val pagingAdapter = MovieListRvAdapter(::onMovieSelected)
    private lateinit var loadState: MovieListErrorAdapter.Holder

    private var currentText: String = ""
    private val handler = Handler(Looper.getMainLooper())
    private val searchRunnable = Runnable {
        viewModel.submitQuery(currentText)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        navController = findNavController()
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationComponent(view)

        // In case of loading errors this callback is called when you tap retry button
        val footerAdapter = MovieListErrorAdapter { pagingAdapter.retry() }
        // Combine adapters
        val adapterWithLoadState = pagingAdapter.withLoadStateFooter(footerAdapter)

        binding.movieListRv.adapter = adapterWithLoadState

        binding.searchEditText.doAfterTextChanged{
            currentText = it?.toString() ?: ""

            handler.removeCallbacks(searchRunnable)
            handler.postDelayed(searchRunnable, 500L)
        }

        binding.filterButton.setOnClickListener{
            val directions = MovieListFragmentDirections
                .actionMovieListFragmentToMovieSortFragment()
            navController.navigate(directions)
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData->
                pagingAdapter.submitData(pagingData)
            }
        }

        loadState = MovieListErrorAdapter.Holder(
            binding.loadStateView
        ) { pagingAdapter.retry() }

        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.debounce(200).collectLatest { state: CombinedLoadStates ->
                if(state.refresh == LoadState.Loading || state.refresh is LoadState.Error) {
                    binding.loadStateView.root.visibility = View.VISIBLE
                    loadState.bind(state.refresh)
                }
                else{
                    binding.loadStateView.root.visibility = View.GONE
                }
            }
        }
    }

    private fun onMovieSelected(movieId: Int){

        val direction = MovieListFragmentDirections
            .actionMovieListFragmentToMovieDetailFragment(movieId)
        navController.navigate(direction)
    }

    private fun setNavigationComponent(view: View){
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<Toolbar>(R.id.toolbarMovieList)
            .setupWithNavController(navController, appBarConfiguration)
    }

}