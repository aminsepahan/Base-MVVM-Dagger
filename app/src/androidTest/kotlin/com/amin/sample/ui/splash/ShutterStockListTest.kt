package com.amin.sample.ui.splash


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.amin.sample.R
import com.amin.sample.utils.Utils.atPosition
import com.amin.sample.utils.childAtPosition
import com.amin.sample.utils.test.EspressoIdlingResource
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ShutterStockListTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SplashActivity::class.java)

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
    @Test
    fun shutterStockListTest() {
        val textView = onView(
            allOf(
                withId(R.id.idleTv),
                withText("To Start, rotate the menu and choose the sample that you want"),
                childAtPosition(
                    allOf(
                        withId(R.id.rootLay),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.widget.ScrollView::class.java),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

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
                withId(R.id.searchEt), withHint("Search Shutter Stock"),
                isDisplayed()
            )
        )
        editText.check(matches(withText("")))
        onView(withId(R.id.rv))
            .check(
                matches(
                    atPosition(
                        1, Matchers.allOf(
                            ViewMatchers.withId(R.id.coverImageView), isDisplayed()
                        )
                    )
                )
            )

    }

}
