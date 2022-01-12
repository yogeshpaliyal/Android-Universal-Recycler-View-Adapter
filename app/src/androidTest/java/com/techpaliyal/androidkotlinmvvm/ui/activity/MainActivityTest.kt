package com.techpaliyal.androidkotlinmvvm.ui.activity

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.techpaliyal.androidkotlinmvvm.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest(){

    @Rule
    @JvmField
    public var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun before(){
       /* setFailureHandler(FailureHandler { error, viewMatcher ->

        })*/
    }

    @Test
    fun launchBasicActivity(){
        onView(withId(R.id.btnBasicListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }


    }

    @Test
    fun launchMultiSelectActivity(){
        onView(withId(R.id.btnMultiSelect)).perform(click()).withFailureHandler { error, viewMatcher ->  }


    }

    @Test
    fun launchLoadingActivity(){
        onView(withId(R.id.btnLoadingListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }
    }

    @Test
    fun launchShimmerActivity(){
        onView(withId(R.id.btnShimmerListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }
    }


    @Test
    fun launchPaginationActivity(){
        onView(withId(R.id.btnPaginationListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }
    }

    @Test
    fun launchBindingAdapterActivity(){
        onView(withId(R.id.btnBindingAdapter)).perform(click()).withFailureHandler { error, viewMatcher ->  }
    }

    @Test
    fun launchMultipleViewsActivity(){
        onView(withId(R.id.btnMultipleViews)).perform(click()).withFailureHandler { error, viewMatcher ->  }
        onView(withId(R.id.btnSchoolListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }
        pressBack()
        onView(withId(R.id.btnChatListing)).perform(click()).withFailureHandler { error, viewMatcher ->  }
        pressBack()
    }

}