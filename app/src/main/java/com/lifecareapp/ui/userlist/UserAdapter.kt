package com.lifecareapp.ui.userlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.lifecareapp.R
import com.lifecareapp.data.remote.model.userlist.SearchResponse
import com.lifecareapp.data.remote.model.userlist.UserData
import com.lifecareapp.utils.GlideApp
import com.lifecareapp.utils.inflate
import kotlinx.android.synthetic.main.rv_item_user_list.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = ArrayList<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(parent.inflate(R.layout.rv_item_user_list))
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    override fun getItemCount(): Int = userList.size


    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            //initialize click listener here
        }

        fun bindItems(userData: UserData) {
            with(itemView) {
                GlideApp.with(this)
                        .load(userData.avatarUrl)
                        .circleCrop()
                        .into(itemView.ivProfilePic)

                tvName.text = userData.login?:""
                tvScore.text= ("${context.getString(R.string.user_score)} ${String.format("%.2f", userData.score)}")
            }
        }
    }

    fun addUserList(data: SearchResponse?) {
        //userList.clear()
        data?.items?.let { userList.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearList() {
        userList.clear()
        notifyDataSetChanged()

    }

}
