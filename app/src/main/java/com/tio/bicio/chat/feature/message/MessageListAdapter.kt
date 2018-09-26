package com.tio.bicio.chat.feature.message

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tio.bicio.chat.R
import com.tio.bicio.chat.domain.Message
import com.tio.bicio.chat.domain.User
import com.tio.bicio.chat.util.formatDateTime


class MessageListAdapter(private val currentUser: User) : RecyclerView.Adapter<MessageListAdapter.MessageHolder>() {

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

    private val messageList: MutableList<Message> = mutableListOf()

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun addItem(message: Message) {
        messageList.add(message)
    }

    fun notifyItemInserted() {
        notifyItemInserted(messageList.size -1)
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]

        return if (message.sender!!.equals(currentUser)) {
            VIEW_TYPE_MESSAGE_SENT
        } else {
            VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view: View

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_sent, parent, false)
            return SentMessageHolder(view)
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_message_received, parent, false)
            return ReceivedMessageHolder(view)
        }

        throw Exception("Wrong view Type")
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messageList[position]
         holder.bind(message)
    }

    abstract class MessageHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        abstract fun bind(message: Message)
    }

    class ReceivedMessageHolder(view : View): MessageHolder(view) {

        private var messageText: TextView = itemView.findViewById(R.id.text_message_body) as TextView
        private var timeText: TextView = itemView.findViewById(R.id.text_message_time) as TextView
        private var nameText: TextView = itemView.findViewById(R.id.text_message_name) as TextView
        private var profileImage: ImageView = itemView.findViewById(R.id.image_message_profile) as ImageView

        override fun bind(message: Message) {
            messageText.text = message.text

            timeText.text = message.createdAt!!.formatDateTime()
            nameText.text = message.sender!!.nickname

//            profileImage.setImageURI(Uri.parse(message.sender?.profileUrl))
        }
    }

    class SentMessageHolder internal constructor(view: View) : MessageHolder(view) {

        private var messageText: TextView = view.findViewById(R.id.text_message_body)
        private var timeText: TextView = view.findViewById(R.id.text_message_time)

        override fun bind(message: Message) {
            messageText.text = message.text
            timeText.text = message.createdAt!!.formatDateTime()
        }
    }
}