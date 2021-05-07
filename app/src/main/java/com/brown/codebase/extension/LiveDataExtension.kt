package com.brown.codebase.extension

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * A wrapper class to reduce boilerplate code when use [LiveData] as a single event
 */
class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun ifNotHandled(): T? = if (hasBeenHandled) {
        null
    } else {
        hasBeenHandled = true
        content
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

}


/**
 * Extension function to observe a `LiveData<Event<T>>` instance,
 * inspired by `LiveData.observer` extension function.
 */
@MainThread
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { event ->
        event.ifNotHandled()?.let(onChanged)
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

