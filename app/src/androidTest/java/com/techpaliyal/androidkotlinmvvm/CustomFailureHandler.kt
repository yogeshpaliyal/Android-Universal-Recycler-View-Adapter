package com.techpaliyal.androidkotlinmvvm

import android.view.View
import androidx.test.espresso.FailureHandler
import org.hamcrest.Matcher

class CustomFailureHandler: FailureHandler {
    override fun handle(error: Throwable?, viewMatcher: Matcher<View>?) {
        assert(false)
    }
}