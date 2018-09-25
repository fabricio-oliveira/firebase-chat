package com.tio.bicio.chat.feature.message

import com.tio.bicio.chat.domain.Message

interface MessageListView {
    val presenter: MessageListPresenter

    fun showError(error: String?)

    fun showSuccess(message: String?)

    fun showItem(message: Message)
}