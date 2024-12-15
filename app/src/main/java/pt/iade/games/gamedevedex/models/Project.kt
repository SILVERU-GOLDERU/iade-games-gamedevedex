package pt.iade.games.gamedevedex.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val id: Int,
    val title: String,
    var votes: Int,
    val detailedDescription: String,
    val assets: List<String>,
    val description: String,
    val groupMembers: List<Student>,
    val semester: Int
): Parcelable
