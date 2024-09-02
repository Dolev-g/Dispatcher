package com.example.dispatcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dispatcher.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dynamically create and add a TextView for the first two words of each article's body
        articleList.mapNotNull { article ->
            article.body?.split(" ")?.take(2)?.joinToString(" ")
        }.forEach { firstTwoWords ->
            val textView = TextView(requireContext()).apply {
                text = firstTwoWords
                textSize = 18f
                setTextColor(resources.getColor(android.R.color.black, null))
            }

            binding.root.addView(textView)
        }

        displayToast("Home Fragment is active!")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
