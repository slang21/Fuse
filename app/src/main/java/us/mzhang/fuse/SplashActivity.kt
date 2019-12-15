package us.mzhang.fuse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val flyRight = AnimationUtils.loadAnimation(
            this@SplashActivity, R.anim.fly_in_right
        )

        val flyLeft = AnimationUtils.loadAnimation(
            this@SplashActivity, R.anim.fly_in_left
        )

        flyLeft.setAnimationListener(
            object : Animation.AnimationListener {
                override fun onAnimationStart(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    var intentAuth = Intent()
                    intentAuth.setClass(
                        this@SplashActivity,
                        AuthActivity::class.java
                    )

                    startActivity(intentAuth)
                    finish()

                }

                override fun onAnimationRepeat(p0: Animation?) {
                }

            }
        )

        ivBrand.startAnimation(flyRight)
        ivLogo.startAnimation(flyLeft)
    }
}
