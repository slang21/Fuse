package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.new_friend_layout.*
import us.mzhang.fuse.R

class NewFriendDialog : DialogFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New Friend")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_friend_layout, null
        )

        tvUserId.text = ""

        return builder.create()
    }

    override fun onResume() {
        super.onResume()
    }
}