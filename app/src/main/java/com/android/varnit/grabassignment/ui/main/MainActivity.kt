package com.android.varnit.grabassignment.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.varnit.grabassignment.R
import com.android.varnit.grabassignment.di.component.DaggerActivityComponent
import com.android.varnit.grabassignment.di.module.ActivityModule
import com.android.varnit.grabassignment.ui.news.NewsAdapter
import com.android.varnit.grabassignment.ui.news.NewsFragment
import com.android.varnit.grabassignment.ui.newsdetail.NewsDetailsFragment
import javax.inject.Inject

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
class MainActivity : AppCompatActivity(), MainContract.View, NewsAdapter.onItemClickListener {
    override fun itemDetail(newsUrl: String) {
        showNewsDetailsFragment(newsUrl)
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }

    override fun showNewsDetailsFragment(newsUrl: String) {
        if (supportFragmentManager.findFragmentByTag(NewsDetailsFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(AnimType.FADE.getAnimPair().first, AnimType.FADE.getAnimPair().second)
                .replace(R.id.frame, NewsDetailsFragment().newInstance(newsUrl), NewsDetailsFragment.TAG)
                .commit()
        }
    }

    override fun showNewsFragment() {
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(
                AnimType.SLIDE.getAnimPair().first,
                AnimType.SLIDE.getAnimPair().second
            )
            .replace(R.id.frame, NewsFragment().newInstance(), NewsFragment.TAG)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(NewsDetailsFragment.TAG)

        if (fragment == null) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    enum class AnimType {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            return when (this) {
                SLIDE -> Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> Pair(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }
}