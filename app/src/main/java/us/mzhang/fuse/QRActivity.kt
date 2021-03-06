package us.mzhang.fuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_qr.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import us.mzhang.fuse.data.User
import us.mzhang.fuse.dialogs.NewFriendDialog
import java.lang.IndexOutOfBoundsException

class QRActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        zxingView.setResultHandler(this)
        zxingView.startCamera()

    }

    override fun onResume() {
        super.onResume()
        zxingView.startCamera()
    }

    override fun onStop() {
        super.onStop()
        zxingView.stopCamera()
    }

    override fun handleResult(qrResult: Result?) {
        addFriend(qrResult!!.text)
        zxingView.setResultHandler(this)
        zxingView.startCamera()
    }

    private fun addFriend(uid: String) {
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        var query = usersRef.whereEqualTo("uid", uid)
            .limit(1)
        query.get().addOnSuccessListener { users ->
            try {
                val friend = users.toObjects(User::class.java)[0]
                val newFriend = NewFriendDialog()
                val bundle = Bundle()
                bundle.putSerializable("USER", friend)
                newFriend.arguments = bundle
                newFriend.show(
                    supportFragmentManager,
                    "NEW_FRIEND"
                )
            } catch (e: IndexOutOfBoundsException) {
                Toast.makeText(
                    this@QRActivity,
                    getString(R.string.qr_read_error),
                    Toast.LENGTH_LONG
                ).show()
            }


        }
    }
}
