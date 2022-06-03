package com.tutorialapp.domain.model

import java.time.ZonedDateTime
import kotlin.math.max

@JvmInline
value class ProductId(val value: String)

sealed class TutorialIcon {
    object Unknown : TutorialIcon()
    class Local(val name: String) : TutorialIcon()
    class Remote(val url: String) : TutorialIcon()
}

class Tutorial private constructor(
    val id: ProductId,
    val name: String,
    val icon: TutorialIcon,
    val description: String,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
    )