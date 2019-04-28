package com.android.varnit.grabassignment.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.varnit.grabassignment.R
import com.android.varnit.grabassignment.di.component.DaggerFragmentComponent
import com.android.varnit.grabassignment.di.module.FragmentModule
import com.android.varnit.grabassignment.models.NewsModel
import com.android.varnit.grabassignment.ui.main.MainActivity
import com.android.varnit.grabassignment.utils.NewsUtils
import kotlinx.android.synthetic.main.fragment_news.*
import javax.inject.Inject

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsFragment : Fragment(), NewsContract.View {

    @Inject
    lateinit var presenter: NewsContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): NewsFragment {
        return NewsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_news, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("Error", error)
    }

    override fun loadDataSuccess(newsData: NewsModel) {
        val articlesList = newsData.articles
        var adapter = NewsAdapter(activity?.baseContext!!, articlesList.toMutableList(), activity as MainActivity)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    private fun initView() {
        if (NewsUtils.isNetworkConnected(activity?.baseContext!!)) {
            presenter.loadData()
            no_internet.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
        else {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            no_internet.visibility = View.VISIBLE
        }
    }

    companion object {
        val TAG: String = "NewsFragment"
    }
}