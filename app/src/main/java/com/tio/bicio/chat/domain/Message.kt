package com.tio.bicio.chat.domain

import com.google.firebase.firestore.DocumentSnapshot
import java.util.*

class Message{

    var id: String?
    var text: String?
    var createdAt: Date?
    var sender: User?


    constructor(id: String?, text:String?, createdAt: Date?, sender: User?){
        this.id = id
        this.text = text
        this.createdAt = createdAt
        this.sender = sender
    }

    constructor(text:String, createdAt: Date, sender: User): this(null,text,createdAt,sender)
    constructor():this(null,null,null,null)

    fun toMap():Map<String,Any?>{
        return hashMapOf(TEXT to this.text,
                CREATED_AT to this.createdAt,
                SENDER to this.sender?.toMap())
    }

    companion object {

        fun create(item: DocumentSnapshot):Message {
            return Message(id= item.id, text = item.get(TEXT) as String?,
                    createdAt = item.get(CREATED_AT) as Date?, sender = User.create(item.get(SENDER) as Map<String, Any?>? ))
        }

        private const val TEXT = "text"
        const val CREATED_AT = "created_at"
        private const val SENDER = "sender"
    }
}
