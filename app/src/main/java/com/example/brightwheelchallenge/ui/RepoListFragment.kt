package com.example.brightwheelchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.brightwheelchallenge.R
import com.example.brightwheelchallenge.data.RepoList
import com.example.brightwheelchallenge.databinding.FragmentRepoListBinding
import com.example.brightwheelchallenge.network.RepoListViewModel

/**
 * Displays list of 100 repos from githubb
 */
class RepoListFragment: Fragment() {

    companion object {
        val FRAGMENT_TAG = RepoListFragment::class.java.simpleName + ".FRAGMENT_TAG"
    }

    // variable declaration
    private lateinit var binding: FragmentRepoListBinding
    private lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup the view model
        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // intialize view binding
        binding = FragmentRepoListBinding.inflate(inflater, container, false)

        // show loading indicator while we get the repos
        binding.dotLoadingView.startLoading()
        viewModel.getRepos()

        // observer to show repos
        viewModel.liveRepos.observe(viewLifecycleOwner, Observer { displayRepos(it) })

        return binding.root
    }

    // display the repos
    private fun displayRepos(repos: RepoList?) {
        // hide loading indicator
        binding.dotLoadingView.stopLoading()

        if (repos != null) {
            binding.repoList.listView.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_activated_1,
                    android.R.id.text1,
                    repos.repoList
            )

            // navigate to contributor list when clicked on the repo
            binding.repoList.listView.onItemClickListener = AdapterView.OnItemClickListener {
                _: AdapterView<*>, _: View, pos: Int, _: Long ->

                val repo = repos.repoList[pos]
                viewModel.getContributors(repo.owner.login, repo.name)

                parentFragmentManager.beginTransaction()
                        .add(
                            R.id.container,
                            ContributorListFragment.INSTANCE,
                            ContributorListFragment.FRAGMENT_TAG
                        )
                        .addToBackStack(FRAGMENT_TAG)
                        .commit()
            }
        } else {
            // show toast if no repos found
            Toast.makeText(requireContext(), "No repos found", Toast.LENGTH_SHORT).show()
        }
    }
}