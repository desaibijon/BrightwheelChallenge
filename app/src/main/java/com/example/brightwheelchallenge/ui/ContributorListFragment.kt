package com.example.brightwheelchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.brightwheelchallenge.data.Contributor
import com.example.brightwheelchallenge.databinding.FragmentContributorListBinding
import com.example.brightwheelchallenge.network.RepoListViewModel

/**
 * List the contribbutors for a repo
 */
class ContributorListFragment: Fragment() {
    companion object {
        val INSTANCE = ContributorListFragment()
        val FRAGMENT_TAG = ContributorListFragment::class.java.simpleName + ".FRAGMENT_TAG"
    }

    // variable declaration
    private lateinit var binding: FragmentContributorListBinding
    private lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup view model
        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContributorListBinding.inflate(inflater, container, false)

        // show loading indicator until there is something to show
        binding.dotLoadingView.startLoading()

        // observer to show contributors
        viewModel.liveContributors.observe(viewLifecycleOwner, Observer<List<Contributor>> {
            displayContributors(it)
        })

        return binding.root
    }

    // display the contributors for the selected repo
    private fun displayContributors(contributors: List<Contributor>?) {
        binding.dotLoadingView.stopLoading()
        if (contributors != null) {
            binding.contributorList.listView.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_activated_1,
                    android.R.id.text1,
                    contributors
            )
        } else {
            // no contributors found for this fragment, show toast and go back to previous page.
            Toast.makeText(
                requireContext(),
                "No contributors found, please go back and try again",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}