package com.amin.sample.ui.splash


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.amin.sample.R
import com.amin.sample.utils.childAtPosition
import com.amin.sample.utils.test.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ShutterStockListAnd4thItemClick {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun shutterStockListAnd4thItemClick() {
        val materialCardView = onView(
            allOf(
                withId(R.id.cv),
                childAtPosition(
                    allOf(
                        withId(R.id.rv),
                        childAtPosition(
                            withId(R.id.rootLay),
                            3
                        )
                    ),
                    0
                )
            )
        )
        materialCardView.perform(scrollTo(), click())

        val editText = onView(
            allOf(
                withId(R.id.searchEt),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withHint(R.string.search_shutter_stock_hint)))
        editText.check(matches(withText("")))

        val materialCardView2 = onView(
            allOf(
                withId(R.id.cv),
                childAtPosition(
                    allOf(
                        withId(R.id.rv),
                        childAtPosition(
                            withClassName(`is`("androidx.coordinatorlayout.widget.CoordinatorLayout")),
                            1
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialCardView2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.caption),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.captionCV),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))
    }
}
