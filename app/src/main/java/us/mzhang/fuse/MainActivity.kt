package us.mzhang.fuse

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import us.mzhang.fuse.data.user


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnScan.isEnabled = false
        requestNeededPermission()

        btnScan.setOnClickListener {
            // launch QRActivity here
            startActivity(Intent(this@MainActivity, QRActivity::class.java))
        }

        btnProfile.setOnClickListener {
            openProfilePage()
        }
    }

    private fun openProfilePage() {
        val db = FirebaseFirestore.getInstance()
        val usersRef = db.collection("users")
        var query = usersRef.whereEqualTo("uid", FirebaseAuth.getInstance().uid)
            .limit(1)
        var currUser = user()
        query.get().addOnSuccessListener { users ->
            if (users.isEmpty) {
                currUser = user(
                    FirebaseAuth.getInstance().uid,
                    "",
                    "",
                    "",
                    "",
                    ""
                )
                usersRef.add(
                    currUser!!
                )
            } else {
                Toast.makeText(this@MainActivity, "works", Toast.LENGTH_LONG).show()
                currUser = users.toObjects(user::class.java)[0]
            }
            var intent = Intent(this@MainActivity, ProfileActivity::class.java)
            intent.putExtra("USER", currUser)
            startActivity(intent)
        }.addOnFailureListener {
            Toast.makeText(
                this@MainActivity,
                "Sorry we are having issues opening profile right noe",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.CAMERA
                )
            ) {
                Toast.makeText(
                    this,
                    "I need it for QR reading", Toast.LENGTH_SHORT
                ).show()
            }
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                101
            )
        } else {
            btnScan.isEnabled = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CAMERA perm granted", Toast.LENGTH_SHORT).show()

                    btnScan.isEnabled = true
                } else {
                    Toast.makeText(this, "CAMERA perm NOT granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
