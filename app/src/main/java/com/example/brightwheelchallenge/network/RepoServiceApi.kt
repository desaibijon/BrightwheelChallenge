package com.example.brightwheelchallenge.network

import com.example.brightwheelchallenge.data.Contributor
import com.example.brightwheelchallenge.data.RepoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * setup for making API requests
 */
interface RepoServiceApi {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val REPO_LIST = "search/repositories?q=stars:>0"
        const val CONTRIBUTOR_LIST = "repos/"
    }
    @GET(REPO_LIST)
    fun getRepos(): Call<RepoList>

    @GET("$CONTRIBUTOR_LIST{owner}/{repo}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("repo")  repo: String
    ): Call<List<Contributor>>
}