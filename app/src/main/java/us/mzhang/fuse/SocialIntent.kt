package us.mzhang.fuse

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


class SocialIntent(val context: Context) {

    val twitterUser = "jack"
    val snapchatId = "testing123"

    fun launchTwitter() {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("twitter://user?screen_name=$twitterUser")
            )
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/#!/$twitterUser")
                )
            )
        }
    }

    fun launchSnapchat() {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/$snapchatId"))
            intent.setPackage("com.snapchat.android")
            context.startActivity(intent)
        } catch (e: Exception) {
            context.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://snapchat.com/add/$snapchatId")
                )
            )
        }

    }
}