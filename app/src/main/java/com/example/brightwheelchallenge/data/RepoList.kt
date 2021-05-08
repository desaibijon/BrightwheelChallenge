package com.example.brightwheelchallenge.data

import com.google.gson.annotations.SerializedName

/**
 * Serialized data class
 */
class RepoList {
    @SerializedName("items")
    val repoList: List<Repo> = mutableListOf()
}