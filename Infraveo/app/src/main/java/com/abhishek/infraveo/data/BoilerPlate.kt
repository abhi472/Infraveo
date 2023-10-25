package com.abhishek.infraveo.data

import com.abhishek.infraveo.R
import com.abhishek.infraveo.chat.ConversationUiState
import com.abhishek.infraveo.chat.Message
import com.abhishek.infraveo.data.EMOJIS.EMOJI_CLOUDS
import com.abhishek.infraveo.data.EMOJIS.EMOJI_FLAMINGO
import com.abhishek.infraveo.data.EMOJIS.EMOJI_MELTING
import com.abhishek.infraveo.data.EMOJIS.EMOJI_PINK_HEART
import com.abhishek.infraveo.data.EMOJIS.EMOJI_POINTS


private val initialMessages = listOf(
    Message(
        "me",
        "Check it out!",
        "8:07 PM"
    ),
    Message(
        "me",
        "Thank you!$EMOJI_PINK_HEART",
        "8:06 PM",
        R.drawable.sticker
    ),
    Message(
        "Nikhil Handa",
        "You can use all the same stuff",
        "8:05 PM"
    ),
    Message(
        "Nikhil Handa",
        "@abhishek LOL, do it, newb",
        "8:05 PM"
    ),
    Message(
        "Bill Murray",
        "They're still asking for apps, dont you have like 7+ years of experience?",
        "8:04 PM"
    ),
    Message(
        "me",
        "Got this task to create an app as an interview, lol,",
        "8:03 PM"
    )
)

val exampleUiState = ConversationUiState(
    initialMessages = initialMessages,
    channelName = "#Infraveo",
    channelMembers = 42
)


object EMOJIS {
    // EMOJI 15
    const val EMOJI_PINK_HEART = "\uD83E\uDE77"

    // EMOJI 14 🫠
    const val EMOJI_MELTING = "\uD83E\uDEE0"

    // ANDROID 13.1 😶‍🌫️
    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"

    // ANDROID 12.0 🦩
    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"

    // ANDROID 12.0  👉
    const val EMOJI_POINTS = " \uD83D\uDC49"
}
