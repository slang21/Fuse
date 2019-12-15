package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_profile.view.*
import us.mzhang.fuse.R
import us.mzhang.fuse.data.User

class NewFriendDialog : DialogFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    private lateinit var tvUserId: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        var newFriend: User = (arguments?.getSerializable("USER") as User)

        builder.setTitle(newFriend.uid)

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_friend_layout, null
        )

        tvUserId = rootView.tvUserId
        tvUserId.text = newFriend.username


        return builder.create()
    }

    override fun onResume() {
        super.onResume()
    }
}