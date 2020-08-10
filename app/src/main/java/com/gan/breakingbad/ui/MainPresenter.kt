package com.gan.breakingbad.ui

import com.gan.breakingbad.service.BreakingBadDataService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable


class MainPresenter() : MainContract.Presenter {

    private val mDisposable = CompositeDisposable()

    private var view: MainContract.View? = null

    //TODO  presenter method communicates with model
    override fun loadData(service: BreakingBadDataService, processScheduler: Scheduler, androidScheduler: Scheduler) {

        mDisposable.add(service.getCharacters().subscribeOn(processScheduler).observeOn(androidScheduler)
            .doOnSubscribe {
                view?.showProgress(true)
            }
            .subscribe({
                view?.showProgress(false)
                view?.loadDataSuccess(it.toList().distinct().sortedWith(compareBy{ it.name }))

            }, {
                view?.showProgress(false)
                view?.showErrorMessage(it.localizedMessage)
            }))

    }


    /**
     * TODO this method will do unsubscribe for disposable rx object
     */
    override fun unsubscribe() {

        mDisposable.clear()
    }


    /**
     * TODO  attach our view to the presenter
     */
    override fun attach(view: MainContract.View) {
        this.view = view
    }

    //TODO on destroy ccall this for preventing memory leaks
    override fun detach() {
        if(this.view != null)
        {
            this.view = null
        }
    }


}