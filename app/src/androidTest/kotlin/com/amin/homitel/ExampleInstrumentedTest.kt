package com.amin.homitel

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.amin.homitel.rules.OkHttpIdlingResourceRule
import com.amin.homitel.ui.posts.PostsFragment
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var mIdlingResource: IdlingResource
    @get:Rule
    var rule = OkHttpIdlingResourceRule()

    @Test
    fun useAppContext() {

        val appContext = InstrumentationRegistry.getInstrumentation().context
        assertEquals("com.amin.homitel.test", appContext.packageName)
    }

    @Test
    fun shouldShowList() {
        launchFragmentInContainer<PostsFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.test))
            .check(matches(withText("test 2")))
    }
}
