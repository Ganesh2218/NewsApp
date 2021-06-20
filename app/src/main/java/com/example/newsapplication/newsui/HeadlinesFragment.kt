package com.example.newsapplication.newsui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.apicall.NewsProperty
import com.example.newsapplication.databinding.FragmentHeadlinesBinding
import com.example.newsapplication.viewmodel.HeadlineViewModel


class HeadlinesFragment : Fragment() {

    private lateinit var binding: FragmentHeadlinesBinding
    private var mAdapter: NewsAdapter? = null
    private val viewModel: HeadlineViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "onActivityCreated()"
        }
        ViewModelProviders.of(this, HeadlineViewModel.Factory(activity.application))
            .get(HeadlineViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines, container, false)

        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        //Navigate to News Detail Page
        viewModel.navigateToDetailNews.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    HeadlinesFragmentDirections.actionHeadlinesFragmentToNewsDetailsFragment(it)
                )
                viewModel.onFinishItemSelected()
            }
        })



        mAdapter = NewsAdapter(NewsClickListener { news ->
            viewModel.onItemSelected(news)
        })



        binding.rvGeneral.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.listNewsGeneral.observe(viewLifecycleOwner, Observer<List<NewsProperty>> {
            it?.apply {
                mAdapter?.news = it
            }
        })



    }

}