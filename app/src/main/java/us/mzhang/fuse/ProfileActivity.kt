package us.mzhang.fuse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_profile.*
import us.mzhang.fuse.adapter.MediaAdapter
import us.mzhang.fuse.data.User
import us.mzhang.fuse.dialogs.AddSocialMedia

class ProfileActivity : AppCompatActivity(), AddSocialMedia.MediaHandler {

    companion object {
        val TAG_ADD = "TAG_ADD"
    }

    val db = FirebaseFirestore.getInstance()
    val usersRef = db.collection("users")
    lateinit var userQuery: Query

    lateinit var currUser: User
    lateinit var mediaAdapter: MediaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        currUser = intent.getSerializableExtra("USER") as User
        userQuery = usersRef.whereEqualTo("uid", currUser.uid)
        initRecyclerView()

        tvUserId.text = FirebaseAuth.getInstance().currentUser?.displayName

//        loadUserLinks(currUser)
    }

    private fun initRecyclerView() {
        mediaAdapter = MediaAdapter(this, currUser)
        recyclerView.adapter = mediaAdapter

        var itemDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )

        recyclerView.addItemDecoration(itemDecoration)
    }


    fun showMediaDialog(user: String, media: String) {
        AddSocialMedia(user, media).show(supportFragmentManager, TAG_ADD)
    }

    override fun updateMedia(username: String, media: String) {
        currUser.socialSet.put(media, username)
        usersRef.document(currUser.uid!!).update("socialSet", currUser.socialSet)
        initRecyclerView()
    }
}