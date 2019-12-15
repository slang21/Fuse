package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_social_layout.view.*
import us.mzhang.fuse.ProfileActivity.Companion.KEY_USER
import us.mzhang.fuse.R

class AddSocialMedia(var username: String) : DialogFragment() {

    interface MediaHandler {
        fun mediaAdded(username: String)
        fun mediaEdited(username: String)
    }

    private lateinit var mediaHandler: MediaHandler

    private lateinit var etUsername: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Add media")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.add_social_layout, null
        )
        etUsername = rootView.etUsername
        builder.setView(rootView)

        if (username != "default here") {
            etUsername.setText(username)
        }

        builder.setPositiveButton("Ok") {
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
                etUsername.error = "Please specify a username"
            }
        }
    }

    fun handleMediaAdd() {
        // tv.text = etUsername.text.toString()
        // save username to firebase
    }
}