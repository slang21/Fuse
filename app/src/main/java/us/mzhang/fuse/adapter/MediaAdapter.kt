package us.mzhang.fuse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import us.mzhang.fuse.R
import kotlinx.android.synthetic.main.profile_media_row_layout.view.*

class MediaAdapter : RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon = itemView.ivIcon
        val tvUsername = itemView.tvUserName
        val btnAddEdit = itemView.btnAddEdit
    }

    var mediaList = mutableListOf<String>("snapchat", "twitter", "instagram")

    val context: Context
    val user: User

    constructor(context: Context, user: User) {
        this.context = context
        this.user = user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mediaRow = LayoutInflater.from(context).inflate(
            R.layout.profile_media_row_layout, parent, false
        )

        return ViewHolder(mediaRow)
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var media = mediaList.get(holder.adapterPosition)

        if (media == "snapchat") {
            holder.ivIcon.setImageResource(R.drawable.ic_action_facebook)
        } else if (media == "twitter") {
//            holder.ivShopping.setImageResource(R.drawable.ic_action_tshirt)
        } else {
//            holder.ivShopping.setImageResource(R.drawable.ic_action_headphones)
        }

        if (user.)

        holder.cbShopping.text = context.getString(R.string.cbText)
        holder.cbShopping.isChecked = shopping.isBought
        holder.tvName.text = shopping.name

        holder.btnDelete.setOnClickListener {
            deleteShopping(holder.adapterPosition)
        }

        holder.cbShopping.setOnClickListener {
            shopping.isBought = holder.cbShopping.isChecked
            updateShopping(shopping)
        }

        holder.btnEdit.setOnClickListener {
            (context as MainActivity).showEditShoppingDialog(
                shopping, holder.adapterPosition
            )
        }

        holder.ibtnInfo.setOnClickListener {
            (context as MainActivity).showInfoDialog(
                shopping, holder.adapterPosition
            )
        }
    }

    fun updateShopping(shopping: Shopping) {
        Thread {
            AppDatabase.getInstance(context).shoppingDao().updateShopping(shopping)
        }.start()
    }

    fun updateShoppingOnPosition(shopping: Shopping, index: Int) {
        shoppingList[index] = shopping
        notifyItemChanged(index)
    }

    fun deleteShopping(index: Int) {
        Thread{
            AppDatabase.getInstance(context).shoppingDao().deleteShopping(shoppingList[index])

            (context as MainActivity).runOnUiThread {
                shoppingList.removeAt(index)
                notifyItemRemoved(index)
            }
        }.start()
    }

    fun deleteAllShopping() {
        Thread {
            AppDatabase.getInstance(context).shoppingDao().deleteAllShopping()

            (context as MainActivity).runOnUiThread {
                shoppingList.clear()
                notifyDataSetChanged()
            }
        }.start()
    }

    fun addShopping(shopping: Shopping) {
        shoppingList.add(shopping)
        notifyItemInserted(shoppingList.lastIndex)
    }

}