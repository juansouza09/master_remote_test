package com.solutionsjs.masterremotetest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solutionsjs.masterremotetest.databinding.FragmentHomeBinding
import com.solutionsjs.masterremotetest.domain.MasterViewModel
import com.solutionsjs.masterremotetest.ui.adapters.MemberAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MasterViewModel by viewModels()

    private lateinit var myAdapter: MemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myAdapter = MemberAdapter()
        viewModel.getMembers()
        viewModel.membersLiveData.observe(viewLifecycleOwner) { member ->
            myAdapter.submitList(member)
        }
        setUpRV()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpRV() {
        binding.rvMembers.layoutManager =
            LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMembers.adapter = myAdapter
    }
}
