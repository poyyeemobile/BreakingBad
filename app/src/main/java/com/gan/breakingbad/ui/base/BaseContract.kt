package com.gan.breakingbad.ui.base

class BaseContract {

    // presenter contract
    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
        fun detach()
    }

    // view Contract, methods inside this contract will change depends view type.
    interface View {

    }
}