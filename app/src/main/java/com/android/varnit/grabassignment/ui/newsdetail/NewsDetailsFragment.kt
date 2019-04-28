package com.android.varnit.grabassignment.ui.newsdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.android.varnit.grabassignment.R
import com.android.varnit.grabassignment.di.component.DaggerFragmentComponent
import com.android.varnit.grabassignment.di.module.FragmentModule
import kotlinx.android.synthetic.main.fragment_news_details.*
import javax.inject.Inject

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class NewsDetailsFragment : Fragment(), NewsDetailsContract.View {

    @Inject
    lateinit var presenter: NewsDetailsContract.Presenter

    private lateinit var rootView: View
    private lateinit var url: String

    companion object {
        const val TAG: String = "NewsDetailsFragment"
        const val URL = TAG + "URL"
    }

    fun newInstance(url: String): NewsDetailsFragment {
        val newsDetailsFragment = NewsDetailsFragment()
        val bundle = Bundle()
        bundle.putString(URL, url)
        newsDetailsFragment.arguments = bundle
        return newsDetailsFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        val bundleData = savedInstanceState ?: arguments
        if (bundleData != null)
            url = bundleData.getString(URL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(
            R.layout.fragment_news_details,
            container,
            false
        )
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
            progress_bar?.visibility = View.VISIBLE
        } else {
            progress_bar?.visibility = View.GONE
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun loadWebViewFromUrl(url: String) {
        val webViewSettings = web_view.settings
        web_view.webChromeClient = MyWebViewChromeClient()
        webViewSettings.javaScriptEnabled = true
        webViewSettings.loadsImagesAutomatically = true
        web_view.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        web_view.loadUrl(url)
    }

    private fun injectDependency() {
        val newsDetailsComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        newsDetailsComponent.inject(this)
    }

    private fun initView() {
        presenter.loadWebView(url)
    }

    private inner class MyWebViewChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            if(newProgress > 40)
                showProgress(false)
            else
                showProgress(true)
        }
    }
}