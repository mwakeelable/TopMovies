package com.wakeel.topmovies.domain

import android.os.Parcel
import android.os.Parcelable
import io.reactivex.Observable

interface MoviesListUseCases {
    fun getMoviesListBy() : Observable<List<MoviesViewModel>>
}

val emptyMoviesViewModel = MoviesViewModel()

data class MoviesViewModel(val id: Int,
                           val title: String,
                           val posterPath: String,
                           val releaseDate: String)
    : Parcelable {

    constructor() : this(0, "", "", "")

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(posterPath)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesViewModel> {
        override fun createFromParcel(parcel: Parcel): MoviesViewModel {
            return MoviesViewModel(parcel)
        }

        override fun newArray(size: Int): Array<MoviesViewModel?> {
            return arrayOfNulls(size)
        }
    }

}