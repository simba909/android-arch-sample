package se.jarbrant.androidarchsample.extensions

val Any.TAG: String
    get() = this.javaClass.simpleName
