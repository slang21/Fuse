package us.mzhang.fuse

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import us.mzhang.fuse.data.User


class SocialIntent(val context: Context, val user: User) {

    fun launchIntent(mediaType: String) {
        var username = user.socialSet.get(mediaType)
        var url = ""
        var playUrl = ""
        if (mediaType == "snapchat") {
            url = "https://snapchat.com/add/$username"
            playUrl = "com.snapchat.android"
        } else if (mediaType == "twitter") {
            url = "twitter://user?screen_name=$username"
            playUrl = "com.twitter.android"
        } else if (mediaType == "instagram") {
            url = "http://instagram.com/_u/$username"
            playUrl = "com.instagram.android"
        }
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            if (mediaType == "snapchat") intent.setPackage("com.snapchat.android")
            else if (mediaType == "instagram") intent.setPackage("com.instagram.android")
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$playUrl"))
            context.startActivity(intent)
        }
    }
}