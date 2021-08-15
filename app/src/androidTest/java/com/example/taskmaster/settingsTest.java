package com.example.taskmaster;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class settingsTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule2=new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void settingsPage() {
        onView(withId(R.id.settingButton)).perform(click());

        onView(withId(R.id.userName)).perform(clearText(),typeText("Aya"), closeSoftKeyboard());

        onView(withId(R.id.saveNameButton)).perform(click());

        Espresso.pressBack();
        onView(withId(R.id.homePageTitle)).check(matches(isDisplayed()));

        onView(withText("Aya's Tasks")).check(matches(isDisplayed()));

    }
}

