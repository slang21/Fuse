package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.add_social_layout.view.*
import us.mzhang.fuse.R

class AddSocialMedia(var tv: TextView) : DialogFragment() {

    private lateinit var etUsername: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Add media")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.add_social_layout, null
        )
        etUsername = rootView.etUsername

        builder.setView(rootView)

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
                handleCityAdd()
                (dialog as AlertDialog).dismiss()
            } else {
                etUsername.error = "No username specified..."
            }
        }
    }

    fun handleCityAdd() {
        tv.text = etUsername.text.toString()
        // save username to firebase
    }
}