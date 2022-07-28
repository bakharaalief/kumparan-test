package com.bakharaalief.kumparantechnicaltest.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.kumparantechnicaltest.data.remote.Result
import com.bakharaalief.kumparantechnicaltest.databinding.FragmentPostBinding
import com.bakharaalief.kumparantechnicaltest.presentation.ViewModelFactory

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: PostListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()
        setUpViewModel()

        mainViewModel.getAllPost().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    adapter.submitList(response.data)
                }
                is Result.Error -> {
                    showLoading(false)
                    binding.errorText.text = response.error
                    binding.postRv.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpViewModel() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(viewModelStore, factory)[MainViewModel::class.java]
    }

    private fun setUpRv() {
        adapter = PostListAdapter()
        binding.postRv.adapter = adapter
        binding.postRv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun showLoading(status: Boolean) {
        binding.loadingIndicator.visibility = if (status) View.VISIBLE else View.GONE
    }
}