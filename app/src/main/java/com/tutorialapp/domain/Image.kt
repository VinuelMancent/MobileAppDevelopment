package com.tutorialapp.domain

sealed class Image {
    object Unknown : Image()
    class Local(val name: String) : Image()
    class Remote(val url: String) : Image()
}