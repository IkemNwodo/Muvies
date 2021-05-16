package com.czech.muvies.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.czech.muvies.MainActivity
import com.czech.muvies.R
import com.czech.muvies.databinding.AiringTodayFragmentBinding
import com.czech.muvies.models.TvShows
import com.czech.muvies.pagedAdapters.AiringTodayMainAdapter
import com.czech.muvies.pagedAdapters.airingTodayItemClickListener
import com.czech.muvies.viewModels.AiringTodayViewModel
//import koleton.api.hideSkeleton
//import koleton.api.loadSkeleton

class AiringTodayFragment : Fragment() {

    private lateinit var viewModel: AiringTodayViewModel
    private lateinit var binding: AiringTodayFragmentBinding

    private val airingTodayClickListener by lazy {
        object : airingTodayItemClickListener {
            override fun invoke(it: TvShows.TvShowsResult) {
                val args = AiringTodayFragmentDirections.actionAiringTodayFragmentToTvShowsDetailsFragment(
                    it, null, null, null, null, null,
                    null, null, null, null, null
                )
                findNavController().navigate(args)
            }

        }
    }
    private val airingTodayAdapter = AiringTodayMainAdapter(airingTodayClickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AiringTodayFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(AiringTodayViewModel::class.java)
        binding.airingTodayVieModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.airingTodayMainList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = airingTodayAdapter

//            loadSkeleton(R.layout.paged_list) {
//
//                color(R.color.colorSkeleton)
//                shimmer(true)
//            }
        }

        viewModel.getAiringTodayList().observe(viewLifecycleOwner, Observer {

//            Handler().postDelayed({
//
//                binding.airingTodayMainList.hideSkeleton()
//
//            }, 2000)

            airingTodayAdapter.submitList(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).showBottomNavigation()
    }

}