package us.mzhang.fuse

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


class SocialIntent(val context: Context) {

    val twitterUser = "jack"
    val snapchatId = "testing123"
    val instaUser = "jimmychin"

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

    fun launchInstagram() {
        val uri = Uri.parse("http://instagram.com/_u/$instaUser")
        val likeIng = Intent(Intent.ACTION_VIEW, uri)

        likeIng.setPackage("com.instagram.android")

        try {
            context.startActivity(likeIng)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/$instaUser")
                )
            )
        }

    }
}