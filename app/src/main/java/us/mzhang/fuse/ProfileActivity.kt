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
    }

}