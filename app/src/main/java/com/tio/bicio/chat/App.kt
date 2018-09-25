package com.tio.bicio.chat

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tio.bicio.chat.di.messageModule
import com.tio.bicio.chat.feature.message.MessageListPresenter
import com.tio.bicio.chat.feature.message.MessageListPresenterImpl
import com.tio.bicio.chat.repository.MessageFirebaseRepository
import com.tio.bicio.chat.repository.MessageRepository
import com.tio.bicio.chat.repository.UserMemoryRepository
import com.tio.bicio.chat.repository.UserRepository
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(context = this,
                  modules = listOf(messageModule))
        instance = this
    }

    companion object {
        private val  TAG  = App::class.java.simpleName
        private var instance : Context? = null
        val context by lazy { instance!!.applicationContext }
    }


}