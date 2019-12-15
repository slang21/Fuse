package us.mzhang.fuse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*
import us.mzhang.fuse.data.user
import us.mzhang.fuse.dialogs.AddSocialMedia

class ProfileActivity : AppCompatActivity() {

    companion object {
        val KEY_USER = "KEY_USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var currUser = intent.getSerializableExtra("USER") as user

//        loadUserLinks(currUser)
    }

//    private fun loadUserLinks(currUser: user) {
//        tvUserId.text = FirebaseAuth.getInstance().currentUser?.displayName
//
//        tvFacebookUsername.text = if (currUser.facebook == "") "N/A" else currUser.facebook; btnAddFacebook.setImageResource(R.drawable.ic_action_edit)
//        tvTwitterUsername.text = if (currUser.twitter == "") "N/A" else currUser.twitter; btnAddTwitter.setImageResource(R.drawable.ic_action_edit)
//        tvSnapchatUsername.text = if (currUser.snapchat == "") "N/A" else currUser.snapchat; btnAddInstagram.setImageResource(R.drawable.ic_action_edit)
//        tvInstagramUsername.text = if (currUser.instagram == "") "N/A" else currUser.instagram; btnAddSnapchat.setImageResource(R.drawable.ic_action_edit)
//        tvLinkedInUsername.text = if (currUser.linkedin == "") "N/A" else currUser.linkedin; btnAddLinkedIn.setImageResource(R.drawable.ic_action_edit)
//
//    }

    fun showNewMediaDialog() {
        // get id of element that called this
        // from there get left constraint and pass into AddSocialMedia(tv: TextView)
        AddSocialMedia().show(supportFragmentManager, KEY_USER)
    }

}