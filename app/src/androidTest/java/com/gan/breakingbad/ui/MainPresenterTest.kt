package com.gan.breakingbad.ui

import com.gan.breakingbad.model.GetCharacters
import com.gan.breakingbad.service.BreakingBadDataService
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations



class MainPresenterTest {

    @Mock
    lateinit var mockService: BreakingBadDataService


    @Mock
    lateinit var view: MainContract.View

    @Mock
    var mockedList: List<GetCharacters>? = null


    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

    }

    @Test
    fun test_LoadData() {


        // Given
        val data = GetCharacters( 1 ,"","","","","","","","")
        val observable = Observable.just(listOf(data))
        val mainPresenter = MainPresenter()
        val scheduler = TestScheduler()
        // When
        Mockito.`when`(mockService.getCharacters()).thenReturn(observable)
        mainPresenter.loadData(mockService, scheduler,scheduler)


        // Then
        verify(view).showProgress(Mockito.anyBoolean())
        verify(view).loadDataSuccess(listOf(data))
        verify(view ,never()).showErrorMessage(Mockito.any())



    }

}