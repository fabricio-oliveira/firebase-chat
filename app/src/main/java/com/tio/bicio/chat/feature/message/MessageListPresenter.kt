package com.tio.bicio.chat.feature.message

import com.tio.bicio.chat.domain.User

interface MessageListPresenter{

    val currentUser: User
    var view: MessageListView?

    fun sendMessage(text: String, onSuccess: (String) -> Unit)

    fun subscribeReceiveMessege()

}