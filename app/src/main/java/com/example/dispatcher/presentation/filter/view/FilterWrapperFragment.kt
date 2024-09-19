package com.example.dispatcher.presentation.filter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.dispatcher.R
import com.example.dispatcher.databinding.FilterWrapperBinding
import com.example.dispatcher.presentation.filter.viewModel.FilterViewModel
import com.example.dispatcher.presentation.search.viewModel.SearchViewModel

class FilterWrapperFragment : Fragment(R.layout.filter_wrapper) {

    private var _binding: FilterWrapperBinding? = null
    private val binding get() = _binding!!
    private val filterViewModel: FilterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FilterWrapperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        replaceFragment(FilterPageFragment())
        observeToFilterChoose()
    }

    private fun observeToFilterChoose() {
        filterViewModel.filterChoose.observe(viewLifecycleOwner) { choose ->
            if (choose) {
                val type = filterViewModel.getFilterType()
                replaceFragment(FilterChooseFragment(type))
            }
            else{
                replaceFragment(FilterPageFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentFilterDisplay, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
