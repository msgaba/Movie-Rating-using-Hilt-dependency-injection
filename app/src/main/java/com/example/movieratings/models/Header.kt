package com.example.movieratings.models

/**
 * Created by Ankita
 */
data class Header(
    var title: String = "",
    var isSelected: Boolean = false,
)

val headerList: MutableList<Header> = arrayListOf(
    Header("Movies", true),
    Header("Tv", false),
    Header("Web Series", false)
)

fun modifyHeader(list: MutableList<Header>, selectedPos: Int): MutableList<Header> {
    list.forEach {
        it.isSelected = false
    }
    list[selectedPos].isSelected = true
    return list
}