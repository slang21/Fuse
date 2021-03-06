package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_social_layout.view.*
import us.mzhang.fuse.R
import java.lang.RuntimeException

class AddSocialDialog(var username: String, val media: String) : DialogFragment() {

    interface MediaHandler {
        fun updateMedia(username: String, media: String)
    }

    private lateinit var mediaHandler: MediaHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MediaHandler) {
            mediaHandler = context
        } else {
            throw RuntimeException(
                getString(R.string.implement_error)
            )
        }
    }

    private lateinit var etUsername: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getString(R.string.add) + " " + media.capitalize())

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.add_social_layout, null
        )
        etUsername = rootView.etUsername
        builder.setView(rootView)

        if (username != getString(R.string.na)) {
            etUsername.setText(username)
        }

        builder.setPositiveButton(getString(R.string.ok)) {
                _, _ ->
        }

        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etUsername.text.isNotEmpty()) {
                handleMediaAdd()
                (dialog as AlertDialog).dismiss()
            } else {
                etUsername.error = getString(R.string.please_enter_username)
            }
        }
    }

    fun handleMediaAdd() {
        mediaHandler.updateMedia(etUsername.text.toString(), media)
    }
}