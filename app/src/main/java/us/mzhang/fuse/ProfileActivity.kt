package us.mzhang.fuse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*
import us.mzhang.fuse.data.user

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var currUser = intent.getSerializableExtra("USER") as user

        loadUserLinks(currUser)
    }

    private fun loadUserLinks(currUser: user) {
        tvUserId.text = FirebaseAuth.getInstance().currentUser.displayName
    }

}