package com.tio.bicio.chat.util

import java.text.SimpleDateFormat
import java.util.*

private val format = SimpleDateFormat("dd/MM/yyy")

fun Date.formatDateTime():CharSequence = format.format(this)