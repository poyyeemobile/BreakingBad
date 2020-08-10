package com.gan.breakingbad.ui

import com.gan.breakingbad.model.GetCharacters
import com.gan.breakingbad.service.BreakingBadDataService
import com.gan.breakingbad.ui.base.BaseContract
import io.reactivex.Scheduler


class MainContract {

    //TODO presenter contract
    interface Presenter : BaseContract.Presenter<MainContract.View>
    {
        fun loadData(service: BreakingBadDataService, processScheduler: Scheduler, androidScheduler: Scheduler)
    }

    //TODO view contract for MainActivity class , we will show a progress bar during data load , if any error happens  it will show error message
    interface View : BaseContract.View{

        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<GetCharacters>)

    }

}