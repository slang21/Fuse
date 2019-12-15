package us.mzhang.fuse

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import us.mzhang.fuse.data.User


class SocialIntent(context: Context, user: User) {

//    val twitterUser = "jack"
//    val snapchatId = "testing123"
//    val instaUser = "jimmychin"
    val context = context
    val user = user


    fun launchIntent(mediaType: String) {
        var username = user.socialSet.get(mediaType)
        var url = ""
        if (mediaType == "snapchat") {
            url = "https://snapchat.com/add/$username"
        } else if (mediaType == "twitter") {
            url = "twitter://user?screen_name=$username"
        } else if (mediaType == "instagram") {
            url = "http://instagram.com/_u/$username"
        }
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            // might have to sed package here :/
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // launch app store
        }
    }

//    fun launchTwitter() {
//        try {
//            val intent = Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("twitter://user?screen_name=$twitterUser")
//            )
//            context.startActivity(intent)
//        } catch (e: ActivityNotFoundException) {
//            context.startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://twitter.com/#!/$twitterUser")
//                )
//            )
//        }
//    }
//
//    fun launchSnapchat() {
//        try {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/$snapchatId"))
//            intent.setPackage("com.snapchat.android")
//            context.startActivity(intent)
//        } catch (e: Exception) {
//            context.startActivity(Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://snapchat.com/add/$snapchatId")
//                )
//            )
//        }
//
//    }
//
//    fun launchInstagram() {
//        val likeIng = Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/$instaUser"))
//        likeIng.setPackage("com.instagram.android")
//        try {
//            context.startActivity(likeIng)
//        } catch (e: ActivityNotFoundException) {
//            context.startActivity(
//                Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("http://instagram.com/$instaUser")
//                )
//            )
//        }
//    }
    // No work
//    fun launchTokTik() {
//        val tiktokIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vm.tiktok.com/$toktikUser"))
//        tiktokIntent.setPackage("com.zhiliaoapp.musically")
//        context.startActivity(tiktokIntent)
//    }
}