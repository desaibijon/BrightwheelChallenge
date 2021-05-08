package com.example.brightwheelchallenge.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.brightwheelchallenge.data.Contributor
import com.example.brightwheelchallenge.data.RepoList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Repository to service API requests
 */
class ReposRepository {

    companion object {
        val INSTANCE: ReposRepository = ReposRepository()
    }

    // Live Data for repos
    private val _liveRepoList = MutableLiveData<RepoList?>()
    val liveRepoList: LiveData<RepoList?> = _liveRepoList

    // // Live Data for contributors
    private val _liveContributors = MutableLiveData<List<Contributor>>()
    val liveContributors: LiveData<List<Contributor>> = _liveContributors

    // initialize service api to make requests
    private val repoServiceApi: RepoServiceApi = RetrofitService.createService(
        RepoServiceApi::class.java
    )

    fun getRepos() {
        repoServiceApi.getRepos().enqueue(object: Callback<RepoList>{
            override fun onFailure(call: Call<RepoList>, t: Throwable) {
                // API call failed, update UI with no repos
                _liveRepoList.postValue(null)
            }

            override fun onResponse(call: Call<RepoList>, response: Response<RepoList>) {
                if (response.isSuccessful) {
                    // API call is successful, show the list of repos
                    _liveRepoList.postValue(response.body())
                }
            }

        })
    }

    fun getContributors(owner: String, repo: String) {
        repoServiceApi.getContributors(owner, repo).enqueue(object: Callback<List<Contributor>>{
            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                _liveContributors.postValue(emptyList())
            }

            override fun onResponse(
                call: Call<List<Contributor>>,
                response: Response<List<Contributor>>
            ) {
                if (response.isSuccessful) {
                    // API call is successful, show the list of repos
                    _liveContributors.postValue(response.body())
                }
            }

        })
    }
}

// use retrofit to make API calls
class RetrofitService {

    companion object {
        private var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(RepoServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }
}