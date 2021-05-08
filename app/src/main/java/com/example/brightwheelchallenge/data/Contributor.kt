package com.example.brightwheelchallenge.data

import com.google.gson.annotations.SerializedName

class Contributor {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("login")
    var login: String = ""
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
    @SerializedName("type")
    var type: String = ""
    override fun toString(): String {
        return login
    }
}