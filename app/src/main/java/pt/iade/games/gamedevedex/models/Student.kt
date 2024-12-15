package pt.iade.games.gamedevedex.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: Int,
    val name: String,
    val avatar: String, // Use String for URI compatibility
    val biography: String,
    val mood: String
) : Parcelable
