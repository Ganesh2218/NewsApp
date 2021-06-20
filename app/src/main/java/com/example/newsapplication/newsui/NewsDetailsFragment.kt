package com.example.newsapplication.newsui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentNewsDetailsBinding


class NewsDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentNewsDetailsBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_news_details, container, false)

        val args = arguments?.let {
            NewsDetailsFragmentArgs.fromBundle(
                it
            )
        }

        binding.backBtn.setOnClickListener {
            findNavController().navigate(NewsDetailsFragmentDirections.actionNewsDetailsFragmentToHeadlinesFragment())

        }

        //(activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding.news = args?.newsProperty


        return binding.root

    }
}