package us.mzhang.fuse.data

import java.io.Serializable

data class user (
    var uid: String? = "",
    var facebook: String = "",
    var twitter: String = "",
    var linkedin: String = "",
    var snapchat: String = "",
    var instagram: String = ""
) : Serializable