package com.example.dispatcher.presentation.filter.view

import SourcesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FilterChooseBinding
import com.example.dispatcher.presentation.filter.view.model.EnumFilter
import com.example.dispatcher.presentation.filter.viewModel.FilterViewModel

class FilterChooseFragment(private val filterType: EnumFilter) : Fragment(R.layout.filter_choose) {

    private var _binding: FilterChooseBinding? = null
    private val binding get() = _binding!!
    private val filterViewModel: FilterViewModel by activityViewModels()
    private lateinit var sourcesAdapter: SourcesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        setListeners()
        setupRecyclerView()
        observeToSources()
        filterViewModel.fetchSources(filterType)
    }

    private fun setupRecyclerView() {
        sourcesAdapter = SourcesAdapter(emptyList(), filterViewModel, filterType)
        binding.sourcesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sourcesAdapter
        }
    }

    private fun observeToSources() {
        filterViewModel.sources.observe(viewLifecycleOwner) { sources ->
            sources?.let {
                sourcesAdapter = SourcesAdapter(it, filterViewModel, filterType)
                binding.sourcesRecyclerView.adapter = sourcesAdapter
            }
        }
    }

    private fun setListeners() {
        binding.backImg.setOnClickListener {
            filterViewModel.setFilterChoose(false)
        }
    }

    private fun setTitle() {
        if (filterType == EnumFilter.SEARCHIN) {
            binding.filterTitle.text = "Search in"
        }

        if (filterType == EnumFilter.SOURCES) {
            binding.filterTitle.text = "Sources"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
