package com.amin.homitel.ui.posts

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amin.homitel.R
import com.amin.homitel.rules.OkHttpIdlingResourceRule
import com.amin.homitel.utils.RecyclerViewMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PostsFragmentTest {

    lateinit var mIdlingResource: IdlingResource
    @get:Rule
    var rule = OkHttpIdlingResourceRule()


    @Test
    fun shouldShowListTestWithSleep() {
        launchFragmentInContainer<PostsFragment>()
        Thread.sleep(7000)
        onView(RecyclerViewMatcher(R.id.rv).atPositionOnView(0, R.id.postTitle))
            .check(matches(withText("sunt aut facere repellat provident occaecati excepturi optio reprehenderit")))
    }

}
