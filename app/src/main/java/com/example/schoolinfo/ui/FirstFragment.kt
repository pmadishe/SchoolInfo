package com.example.schoolinfo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolinfo.SchoolListAdapter
import com.example.schoolinfo.databinding.FragmentFirstBinding
import com.example.schoolinfo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private lateinit var schoolAdapter: SchoolListAdapter

    private var _binding: FragmentFirstBinding? = null
    private val schoolViewModel by viewModels<MainViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            schoolViewModel.getSchoolsList()
            schoolViewModel.response.observe(viewLifecycleOwner) { response ->

                schoolAdapter = SchoolListAdapter(findNavController()) { clickedItem ->
                    schoolViewModel.onSchoolItemClicked(clickedItem)
                }

                val data = response.data
                schoolAdapter.differ.submitList(data)

                rv.apply {
                    layoutManager = LinearLayoutManager(requireContext())

                    adapter = schoolAdapter
                }

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}