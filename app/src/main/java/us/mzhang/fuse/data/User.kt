package us.mzhang.fuse.data

import java.io.Serializable

data class User(
    var uid: String? = "",
    var username: String = "",
    var socialSet: MutableMap<String, String> = mutableMapOf()
) : Serializable