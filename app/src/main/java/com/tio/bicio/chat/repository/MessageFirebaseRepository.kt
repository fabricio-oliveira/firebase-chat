package com.tio.bicio.chat.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction;
import com.tio.bicio.chat.domain.Message
import com.tio.bicio.chat.domain.User
import java.util.*


class MessageFirebaseRepository:MessageRepository {
    private var dr: CollectionReference
    private var timestamp: Date

    init {
        val db = FirebaseFirestore.getInstance()
        dr = db.collection(MESSAGE_COLLECTION)
        timestamp = Date(0)
    }

    override fun save(message: Message, onSuccess: (String) -> Unit) {
        val map = toDatabase(message)
        dr.add(map)
                .addOnSuccessListener{onSuccess(it.id)}
                .addOnFailureListener{ it -> throw Exception ("${it.message}|${it.cause}")}

    }

    override fun registerOnDataChanges(onNext: (items: Message) -> Unit,
                                       onError: (throwable: Throwable) -> Unit) {
        dr.whereGreaterThan(Message.CREATED_AT, timestamp)
            .orderBy(Message.CREATED_AT, Direction.ASCENDING)
                .addSnapshotListener { value, _ ->
                    try {
                        value?.forEach { doc -> onNext(toMemory(doc)) }
                    } catch (ex: Exception) {
                        onError(ex)
                    }
                }

    }

    private fun toDatabase(message: Message) = message.toMap()

    private fun toMemory(item: DocumentSnapshot): Message {
        return Message.create(item)
    }

   companion object {
       private const val MESSAGE_COLLECTION = "message"
   }
}


