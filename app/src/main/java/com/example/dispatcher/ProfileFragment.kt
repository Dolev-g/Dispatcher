package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dispatcher.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dynamically create and add a TextView for each author's name
        articleList.mapNotNull { it.author }.forEach { author ->
            val textView = TextView(requireContext()).apply {
                text = author
                textSize = 18f
                setTextColor(resources.getColor(android.R.color.black, null))
            }
            binding.root.addView(textView)
        }

        displayToast("Profile Fragment is active!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
