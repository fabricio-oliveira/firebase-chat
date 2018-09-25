package com.tio.bicio.chat.di

import com.tio.bicio.chat.feature.message.MessageListPresenter
import com.tio.bicio.chat.feature.message.MessageListPresenterImpl
import com.tio.bicio.chat.repository.MessageFirebaseRepository
import com.tio.bicio.chat.repository.MessageRepository
import com.tio.bicio.chat.repository.UserMemoryRepository
import com.tio.bicio.chat.repository.UserRepository
import org.koin.dsl.module.module


val messageModule  = module {
    single { MessageListPresenterImpl(get(),get())  as MessageListPresenter }
    single { UserMemoryRepository() as UserRepository }
    single { MessageFirebaseRepository() as MessageRepository }
}