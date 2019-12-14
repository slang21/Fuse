package us.mzhang.fuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_qr.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QRActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)

        zxingView.setResultHandler(this)
        zxingView.startCamera()

        var social = SocialIntent(this)

        btnTwitter.setOnClickListener {
            social.launchTwitter()
        }

        btnSnapchat.setOnClickListener {
            social.launchSnapchat()
        }
    }

    override fun onStop() {
        super.onStop()
        zxingView.stopCamera()
    }

    override fun handleResult(qrResult: Result?) {
        test.text = qrResult?.text
        zxingView.setResultHandler(this)
        zxingView.startCamera()
    }
}
