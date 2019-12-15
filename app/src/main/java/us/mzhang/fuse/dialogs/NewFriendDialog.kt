package us.mzhang.fuse.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.view.*
import us.mzhang.fuse.R
import us.mzhang.fuse.adapter.MediaAdapter
import us.mzhang.fuse.data.User

class NewFriendDialog : DialogFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    lateinit var mediaAdapter: MediaAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        var newFriend: User = (arguments?.getSerializable("USER") as User)

        builder.setTitle(newFriend.username)

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_friend_layout, null
        )

        mediaAdapter = MediaAdapter(context!!, newFriend)
        recyclerView.adapter = mediaAdapter

        var itemDecoration = DividerItemDecoration(
            context!!,
            DividerItemDecoration.VERTICAL
        )

        recyclerView.addItemDecoration(itemDecoration)
        builder.setView(rootView)


        return builder.create()
    }

    override fun onResume() {
        super.onResume()
    }
}