package com.example.schoolinfo.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.schoolinfo.R
import com.example.schoolinfo.databinding.FragmentSecondBinding
import com.example.schoolinfo.viewmodel.SchoolDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

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

        binding.apply {
            val res = detailsViewModel.getSchoolDetails()
            Log.d("TAG", "onViewCreated: "+res.toString())
            detailsViewModel.getSchoolDetails()
            detailsViewModel.response.observe(viewLifecycleOwner, Observer {list->
//                println(it.toString())
                list.find {
                    it.dbn == args.dbn
                } ?.let {
                    mathScore.text = it.sat_math_avg_score
                    readingScore.text = it.sat_critical_reading_avg_score
                    writingScore.text = it.sat_writing_avg_score
                }
            })

            detailsViewModel.responseSchool.observe(viewLifecycleOwner, Observer {
                mathScore.text = "Math Score : "+ it.sat_math_avg_score
                readingScore.text = "Reading score : "+it.sat_critical_reading_avg_score
                writingScore.text = "Writing Score : " +it.sat_writing_avg_score
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
