package com.adrian.payment.contacts.state

sealed class ContactListState {
    object MainLoading: ContactListState()
    object MainLoadSuccess: ContactListState()
    class MainLoadError(val throwable: Throwable): ContactListState()

    object SecondLoading: ContactListState()
    object SecondLoadSuccess: ContactListState()
    class SecondLoadError(val throwable: Throwable): ContactListState()

    object Selecting: ContactListState()
    class RePainting(val newPosContact: Int): ContactListState()
}
