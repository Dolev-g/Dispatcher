package com.example.dispatcher.presentation.filter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FilterPageBinding
import com.example.dispatcher.presentation.filter.view.model.EnumFilter
import com.example.dispatcher.presentation.filter.viewModel.FilterViewModel

class FilterPageFragment : Fragment(R.layout.filter_page) {

    private var _binding: FilterPageBinding? = null
    private val binding get() = _binding!!
    private val filterViewModel: FilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeSelectedFilterSearchIN()
        observeSelectedFilterSource()
    }

    private fun observeSelectedFilterSearchIN() {
        filterViewModel.searchInChoosen.observe(viewLifecycleOwner) { str ->
            binding.searchInSelected.text = str
        }
    }

    private fun observeSelectedFilterSource() {
        filterViewModel.sourcesChoosen.observe(viewLifecycleOwner) { str ->
            binding.sourcesSelected.text = str
        }
    }

    private fun setListeners() {
        binding.searchInWrapper.setOnClickListener {
            filterViewModel.setFilterType(EnumFilter.SEARCHIN)
            filterViewModel.setFilterChoose(true)
        }

        binding.sourcesWrapper.setOnClickListener {
            filterViewModel.setFilterType(EnumFilter.SOURCES)
            filterViewModel.setFilterChoose(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
