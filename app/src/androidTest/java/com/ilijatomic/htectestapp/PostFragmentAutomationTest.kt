package com.ilijatomic.htectestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ilijatomic.htectestapp.ui.feed.FeedAdapter
import com.ilijatomic.htectestapp.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PostFragmentAutomationTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_PostScreenShown() {
        Thread.sleep(5000)
        onView(withId(R.id.feed_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<FeedAdapter.ViewHolder>(1, click()))
        onView(withId(R.id.post_fragment_parent_tag)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(R.id.post_info)).check(matches(isDisplayed()))
    }
}