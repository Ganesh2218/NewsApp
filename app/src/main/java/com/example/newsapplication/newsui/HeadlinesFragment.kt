package com.example.newsapplication.newsui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentHeadlinesBinding


class HeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines, container, false)


        return binding.root
    }

}