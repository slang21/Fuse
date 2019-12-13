package us.mzhang.fuse

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_login.*

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginClick(v: View) {
        if (!isFormValid()) {
            return
        }


        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            etUsername.text.toString(), etPassword.text.toString()
        ).addOnSuccessListener {

            val user = it.user

            startActivity(
                Intent(
                    this@AuthActivity,
                    MainActivity::class.java
                )
            )

        }.addOnFailureListener {

            Toast.makeText(this@AuthActivity, "Error: ${it.message}", Toast.LENGTH_LONG).show()

        }


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

                user.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(userNameFromEmail(etUsername.text.toString()))
                        .build()
                )
                Toast.makeText(
                    this@AuthActivity,
                    "Welcome ${user.displayName.toString()}}",
                    Toast.LENGTH_LONG
                ).show()
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