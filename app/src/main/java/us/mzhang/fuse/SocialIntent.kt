package us.mzhang.fuse

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


class SocialIntent(val context: Context) {

    val twitterUser = "jack"

    fun launchTwitter() {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("twitter://user?screen_name=${twitterUser}")
            )
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/#!/${twitterUser}")
                )
            )
        }

    }
}