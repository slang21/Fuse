package us.mzhang.fuse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class AuthActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val usersRef = db.collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginClick(v: View) {
        if (!isFormValid()) {
            return
        }
        btnLogin.isEnabled = false

        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            etUsername.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {

            val user = it.user

            var query = usersRef.whereEqualTo("uid", user.uid)
                .limit(1)
            query.get().addOnSuccessListener { users ->
                var currUser = users.toObjects(us.mzhang.fuse.data.User::class.java)[0]
                var intent = Intent(
                    this@AuthActivity,
                    MainActivity::class.java
                )
                intent.putExtra("USER", currUser)
                startActivity(
                    intent
                )
                finish()
            }.addOnFailureListener {
                Toast.makeText(
                    this@AuthActivity,
                    "Error retrieving profile",
                    Toast.LENGTH_LONG
                )
            }

        }.addOnFailureListener {

            Toast.makeText(this@AuthActivity, "Error: ${it.message}", Toast.LENGTH_LONG).show()

        }
        btnLogin.isEnabled = true

    }

    fun prepRegister(v: View) {
        tvTitle.text = "Register"
        btnLogin.visibility = View.GONE
        btnRegister.visibility = View.VISIBLE
        tvRegister.visibility = View.GONE
        tvLogin.visibility = View.VISIBLE
    }

    fun prepLogin(v: View) {
        tvTitle.text = "Login"
        btnLogin.visibility = View.VISIBLE
        btnRegister.visibility = View.GONE
        tvRegister.visibility = View.VISIBLE
        tvLogin.visibility = View.GONE
    }

    fun registerClick(v: View) {
        if (!isFormValid()) {
            return
        } else {

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                etUsername.text.toString(), etPassword.text.toString()
            ).addOnSuccessListener {
                val user = it.user

                var displayName = userNameFromEmail(etUsername.text.toString())

                user.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName)
                        .build()
                )

                var initArr = mutableMapOf<String, String>()

                var currUser = us.mzhang.fuse.data.User(
                    user.uid,
                    displayName,
                    initArr
                )
                usersRef.add(
                    currUser!!
                )

                Toast.makeText(
                    this@AuthActivity,
                    "Welcome $displayName",
                    Toast.LENGTH_LONG
                ).show()
                loginClick(btnLogin)
            }.addOnFailureListener {
                Toast.makeText(this@AuthActivity, "Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun userNameFromEmail(email: String) = email.substringBefore('@')

    private fun isFormValid(): Boolean {
        return when {
            etUsername.text.isEmpty() -> {
                etUsername.error = "This field can not be empty"
                false
            }
            etPassword.text.isEmpty() -> {
                etPassword.error = "This field can not be empty"
                false
            }
            else -> true
        }
    }
}