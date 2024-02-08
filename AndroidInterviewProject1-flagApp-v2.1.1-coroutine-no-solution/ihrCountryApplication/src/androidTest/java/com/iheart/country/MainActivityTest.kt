package com.iheart.country

import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iheart.country.data.Country
import com.iheart.country.ui.detail.DetailComposeActivity
import com.iheart.country.ui.home.MainActivity
import com.iheart.country.ui.home.MainViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainActivityTest {

    @BindValue
    @JvmField
    val viewModel = mockk<MainViewModel>(relaxed = true)


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val _countriesLiveData = MutableLiveData<List<Country>>()

    private val list: MutableList<Country> = mutableListOf()


    @Before
    fun setUp() {
        Intents.init()
        val country1= Country("1",
            "",
            "",
            "",
            "",
            "",
            "",
            1000,
            emptyList(),
            "",
            listOf("1", "1", "1"))
        list.add(country1)
        every { viewModel.countriesLiveData } returns _countriesLiveData
    }
    @Test
    fun initialTest() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.progress)).check(matches(isDisplayed()))


    }

    @Test
    fun loadEmptyDataTest() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        _countriesLiveData.postValue(emptyList())
        onView(withId(R.id.gridview)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.null_textView)).check(matches(isDisplayed()))
    }

    @Test
    fun loadSuccessfulData() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        _countriesLiveData.postValue(list)
        onView(withId(R.id.null_textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.progress)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        onView(withId(R.id.gridview)).check(matches(isDisplayed()))
    }

    @Test
    fun testItemClick() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        _countriesLiveData.postValue(list)
        onView(withId(R.id.null_textView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

        ;
        onView(ViewMatchers.withId(R.id.title)).perform(click());
        intended(hasComponent(DetailComposeActivity::class.java.name))
    }

    @After
    fun afterTest() {
        Intents.release();

    }
}
