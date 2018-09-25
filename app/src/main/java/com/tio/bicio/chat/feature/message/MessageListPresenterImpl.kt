package com.tio.bicio.chat.feature.message

import com.tio.bicio.chat.domain.Message
import com.tio.bicio.chat.domain.User
import com.tio.bicio.chat.repository.MessageRepository
import com.tio.bicio.chat.repository.UserRepository
import java.util.*

class MessageListPresenterImpl(userRepository: UserRepository,
                               private val messageRepository: MessageRepository): MessageListPresenter {

    override val currentUser = userRepository.currentUser ?: User("xxx","aaa","uuu") //throw Exception("User not defined")
    override var view: MessageListView? = null

    override fun subscribeReceiveMessege() {
        messageRepository.registerOnDataChanges(
                onNext = { m -> view!!.showItem(m)},
                onError = { e -> view!!.showError(e.message) })
    }



    override fun sendMessage(text: String, onSuccess: (String) -> Unit){
        Runnable {
            val message = Message(text, Calendar.getInstance().time, currentUser)
            messageRepository.save(message) { print(it) }
        }.run()
    }



}