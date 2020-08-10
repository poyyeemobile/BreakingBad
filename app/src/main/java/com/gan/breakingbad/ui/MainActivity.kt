package com.gan.breakingbad.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.gan.breakingbad.R
import com.gan.breakingbad.model.GetCharacters
import com.gan.breakingbad.service.BreakingBadDataService
import com.gan.breakingbad.utils.ItemDecorator
import com.gan.breakingbad.utils.ViewUtils
import com.gan.breakingbad.utils.breakingBadApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    @Inject
    lateinit var mainPresenter: MainPresenter

    @Inject
    lateinit var apiService: BreakingBadDataService
    private var searchView: SearchView? = null

    private val breakingBadAdapter: BreakingBadAdapter = BreakingBadAdapter(this)
    private val seasonItems = arrayOf("All Seasons", "1", "2", "3", "4", "5")
    private var selectedSeason: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initViews()

        breakingBadApp.component.inject(this)

        mainPresenter.attach(this)

        mainPresenter.loadData(apiService, Schedulers.io(), AndroidSchedulers.mainThread())
    }

    override fun showProgress(show: Boolean) {

        if (show) {
            progress.visibility = View.VISIBLE
        } else {

            progress.visibility = View.GONE
        }

    }

    override fun showErrorMessage(error: String) {

        Log.v(TAG, error)
    }

    override fun loadDataSuccess(getCharacters: List<GetCharacters>) {

        breakingBadAdapter.updateCharacters(getCharacters)
    }


    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.unsubscribe()
        mainPresenter.detach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        //TODO Associate searchable configuration with the SearchView
        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView
        searchView!!.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView!!.setMaxWidth(Int.MAX_VALUE)
        //TODO listening to search query text change
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { //TODO filter recycler view when query submitted
                breakingBadAdapter.getFilter()?.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean { //TODO filter recycler view when text is changed
                breakingBadAdapter.getFilter()?.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() { // close search view on back button pressed
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            return
        }
        super.onBackPressed()
    }

    private fun initViews() {

        val statusAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, seasonItems)
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spSeasons.adapter = statusAdapter

        spSeasons.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> selectedSeason = 1
                    1 -> selectedSeason = 2
                    2 -> selectedSeason = 3
                    3 -> selectedSeason = 4
                    4 -> selectedSeason = 5
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val gridColumnsSize = ViewUtils.calculateNoOfColumns(this)
        val gridLayoutManager = GridLayoutManager(this,gridColumnsSize)
        val itemDecorator = ItemDecorator(10, gridColumnsSize)
        rvBreakingBad.layoutManager = gridLayoutManager
        rvBreakingBad.addItemDecoration(itemDecorator)
        rvBreakingBad.adapter = breakingBadAdapter

    }


}
