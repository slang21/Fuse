package us.mzhang.fuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import us.mzhang.fuse.data.User

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var currUser = intent.getSerializableExtra("USER") as User

        loadUserLinks(currUser)
    }

    private fun loadUserLinks(currUser: User) {
        tvUserId.text = FirebaseAuth.getInstance().currentUser?.displayName

        tvFacebookUsername.text =
            if ("facebook" in currUser.socialSet) currUser.socialSet.get("facebook") else "N/A"
        tvTwitterUsername.text =
            if ("twitter" in currUser.socialSet) currUser.socialSet.get("twitter") else "N/A"
        tvSnapchatUsername.text =
            if ("snapchat" in currUser.socialSet) currUser.socialSet.get("snapchat") else "N/A"
        tvInstagramUsername.text =
            if ("instagram" in currUser.socialSet) currUser.socialSet.get("instagram") else "N/A"
        tvLinkedInUsername.text =
            if ("linkedin" in currUser.socialSet) currUser.socialSet.get("linkedin") else "N/A"

    }

}