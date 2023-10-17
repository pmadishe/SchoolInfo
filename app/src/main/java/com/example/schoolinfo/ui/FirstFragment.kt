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

            // Inside FirstFragment's onViewCreated() or where you set up the RecyclerView
            schoolAdapter = SchoolListAdapter{ clickedItem ->
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(clickedItem.dbn)
                findNavController().navigate(action)
            }
            rv.layoutManager = LinearLayoutManager(requireContext())
            rv.adapter = schoolAdapter

//          Observe LiveData for updates and submit the list when data changes
            schoolViewModel.response.observe(viewLifecycleOwner) { response ->
                schoolAdapter.submitList(response)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}