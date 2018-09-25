package com.tio.bicio.chat.repository

import com.tio.bicio.chat.domain.Message

interface MessageRepository {

    fun save(message: Message, onSuccess: (String) -> Unit)

    fun registerOnDataChanges(onNext: (items: Message) -> Unit,
                                       onError: (throwable: Throwable) -> Unit)
}