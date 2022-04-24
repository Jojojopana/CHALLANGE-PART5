package com.binar.challange_part5.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailMovie(
    val title: String,
    val keterangan: String,
    val image: String,
    val rating: String,
): Parcelable