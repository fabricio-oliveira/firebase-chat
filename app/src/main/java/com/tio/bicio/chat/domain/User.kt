package com.tio.bicio.chat.domain

import com.google.firebase.firestore.DocumentSnapshot

class User{

    var id: String?
    var nickname: String?
    var profileUrl: String?

    constructor() {
        this.id = null
        this.nickname = null
        this.profileUrl = null
    }

    constructor(id: String, nickname: String, profileUrl: String){
        this.id = id
        this.nickname = nickname
        this.profileUrl = profileUrl
    }

    override fun equals(other :Any?):Boolean{
        return when (other) {
            null -> false
            is User -> other.id == this.id
            else -> false
        }
    }

    fun toMap():Map<String,Any?> {
        return hashMapOf("id" to this.id,
                         "nickname" to this.nickname,
                         "profileUrl" to this.profileUrl)
    }

    companion object {
        private const val ID = "id"
        private const val NICKNAME = "nickname"
        private const val PROFILE_URL =  "profileUrl"

        fun create(item: Map<String,Any?>?):User{
            if (item == null)
                return User()

            return User(id= item[ID] as String,
                        nickname = item[NICKNAME] as String,
                        profileUrl = item.get(PROFILE_URL) as String)
        }
    }
}