package com.tio.bicio.chat.repository

import com.tio.bicio.chat.domain.User


class UserMemoryRepository: UserRepository{
    override val currentUser: User? = null
}