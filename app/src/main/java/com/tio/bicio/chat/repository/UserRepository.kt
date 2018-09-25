package com.tio.bicio.chat.repository

import com.tio.bicio.chat.domain.User


interface UserRepository{
    val currentUser: User?
}