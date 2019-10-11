package com.amin.sample.ui.main


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.amin.sample.R
import com.amin.sample.base.MainActivity
import com.amin.sample.utils.childAtPosition
import com.amin.sample.utils.test.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ImgurFragsShowingTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun imgurFragsShowingTest() {
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
                    1
                )
            )
        )
        materialCardView.perform(scrollTo(), click())

        onView(withText("HOT")).check(matches(isDisplayed()))
        onView(withText("TOP")).check(matches(isDisplayed()))
        onView(withText("USER")).check(matches(isDisplayed()))

        onView(
            allOf(
                childAtPosition(withId(R.id.appBar), 0)
            )
        ).check(matches(isDisplayed()))

    }

}
