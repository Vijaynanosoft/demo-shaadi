package com.shaadi.assignment.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaadi.assignment.R
import com.shaadi.assignment.data.local.db.entity.User
import com.shaadi.assignment.data.local.db.typeconverters.UserStatus
import kotlinx.android.synthetic.main.adapter_user_list.view.*

class UserListAdapter(val userList: ArrayList<User>,
                      var iUpdateStatusHandler: IUpdateStatusHandler
) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_user_list, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class UserViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        init {
            itemView.img_accept.setOnClickListener {
                iUpdateStatusHandler.updateStatus(
                    UserStatus.ACCEPTED,
                    userList.get(adapterPosition).id
                )
            }
            itemView.img_decline.setOnClickListener {
                iUpdateStatusHandler.updateStatus(
                    UserStatus.REJECTED,
                    userList.get(adapterPosition).id
                )
            }
        }

        fun bind(position: Int) {
            itemView.txtUserName.text = "${userList.get(position).name}"
            itemView.txtAge.text = "${userList.get(position).age}"
            Glide.with(itemView.context).load(userList.get(position).imgUrl).into(itemView.imgProfile)
            setUserStatus(userList.get(position).user_status)
        }

        private fun setUserStatus(userStatus: UserStatus) {
            when (userStatus) {
                UserStatus.ACCEPTED -> {
                    itemView.tv_accept_reject_status.visibility = View.VISIBLE
                    itemView.grpAcceptAndDeclineButton.visibility = View.GONE
                    itemView.tv_accept_reject_status.text =
                        itemView.tv_accept_reject_status.resources.getString(R.string.accepted)
                }
                UserStatus.REJECTED -> {
                    itemView.tv_accept_reject_status.visibility = View.VISIBLE
                    itemView.grpAcceptAndDeclineButton.visibility = View.GONE
                    itemView.tv_accept_reject_status.text =
                        itemView.tv_accept_reject_status.resources.getString(R.string.rejected)
                }
                UserStatus.PENDING -> {
                    itemView.tv_accept_reject_status.visibility = View.GONE
                    itemView.grpAcceptAndDeclineButton.visibility = View.VISIBLE
                }
            }
        }
    }

    interface IUpdateStatusHandler {
        fun updateStatus(userStatus: UserStatus, id: Long)
    }


}