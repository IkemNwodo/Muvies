package com.czech.muvies.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.czech.muvies.pagedAdapters.DiscoverListAdapter

import com.czech.muvies.databinding.FeaturedFragmentBinding
import com.czech.muvies.viewModels.FeaturedViewModel

class FeaturedFragment : Fragment() {

    private lateinit var viewModel: FeaturedViewModel
    private lateinit var binding: FeaturedFragmentBinding

    private var discoverAdapter =
        DiscoverListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FeaturedFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(FeaturedViewModel::class.java)
        binding.lifecycleOwner = this
        binding.featuredViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            discoverListRecycler.apply {
                layoutManager = LinearLayoutManager(activity,  LinearLayoutManager.VERTICAL, false)
                adapter = discoverAdapter
            }
        }

        viewModel.getDiscoverList().observe(viewLifecycleOwner, Observer {
            discoverAdapter.submitList(it)
        })
    }

}