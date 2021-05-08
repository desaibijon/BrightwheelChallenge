package com.example.brightwheelchallenge.data

import com.google.gson.annotations.SerializedName

class Repo {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("node_id")
    var nodeId: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("full_name")
    var fullName: String = ""
    @SerializedName("private")
    var private: Boolean = false
    @SerializedName("owner")
    lateinit var owner: Owner
    @SerializedName("description")
    var description: String = ""
    @SerializedName("contributors_url")
    var contributorsUrl: String = ""
    override fun toString(): String {
        return name
    }
}

class Owner {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("login")
    var login: String = ""
    @SerializedName("node_id")
    var nodeId: String = ""
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
    @SerializedName("gravatar_id")
    var gravatarId: String = ""
    @SerializedName("url")
    var url: String = ""
    @SerializedName("html_url")
    var htmlUrl: String = ""
    @SerializedName("followers_url")
    var followersUrl: String = ""
    @SerializedName("following_url")
    var followingUrl: String = ""
}