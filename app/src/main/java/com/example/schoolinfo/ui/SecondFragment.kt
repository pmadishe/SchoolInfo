package com.example.schoolinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.schoolinfo.R
import com.example.schoolinfo.databinding.FragmentSecondBinding
import com.example.schoolinfo.viewmodel.SchoolDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    // This binding property is initialized in onCreateView and
    // and removed in onDestroyView by making null.
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!
    private val detailsViewModel by viewModels<SchoolDetailsViewModel>()
    private val args: SecondFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationIcon(com.google.android.material.R.drawable.ic_arrow_back_black_24)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val args = SecondFragmentArgs.fromBundle(requireArguments())
        binding.apply {
            detailsViewModel.getSchoolDetails(args.dbn)

            detailsViewModel.response.observe(viewLifecycleOwner) { value ->
                val data = value.data

                if (!data.isNullOrEmpty()) {
                    for (i in data.indices) {
                        val index = data[i]

                        val mathAvgScore = index.sat_math_avg_score
                        val readingAvgScore = index.sat_critical_reading_avg_score
                        val writingAvgScore = index.sat_writing_avg_score

                        val mathText = "Avg Math score : $mathAvgScore"
                        val readingText = "Avg Reading score : $readingAvgScore"
                        val writingText = "Avg Writing score : $writingAvgScore"

                        binding.mathScore.text = mathText
                        binding.readingScore.text = readingText
                        binding.writingScore.text = writingText
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}