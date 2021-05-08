package com.example.brightwheelchallenge.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.brightwheelchallenge.data.Contributor
import com.example.brightwheelchallenge.data.RepoList

/**
 * This view model handles getting the repos and contributors and updating live data to change UI
 */
class RepoListViewModel: ViewModel() {

    // variable declaration
    val liveRepos = MutableLiveData<RepoList?>()
    val liveContributors = MutableLiveData<List<Contributor>>()

    // initialize the repository
    private val reposRepository: ReposRepository = ReposRepository.INSTANCE

    private val reposObserver = Observer<RepoList?> { repoList ->
        // update UI with list of repos
        liveRepos.postValue(repoList)
    }

    private val contributorsObserver = Observer<List<Contributor>> { contributorList ->
        // update UI with list of contribubtors
        liveContributors.postValue(contributorList)
    }

    // start observing
    init {
        reposRepository.liveRepoList.observeForever(reposObserver)
        reposRepository.liveContributors.observeForever(contributorsObserver)
    }

    // remove obbservers if the view model is destroyed.
    override fun onCleared() {
        reposRepository.liveRepoList.removeObserver(reposObserver)
        reposRepository.liveContributors.removeObserver(contributorsObserver)
    }

    // gets repositories
    fun getRepos() {
        reposRepository.getRepos()
    }

    // gets contributors
    fun getContributors(owner: String, repo: String) {
        reposRepository.getContributors(owner, repo)
    }
}