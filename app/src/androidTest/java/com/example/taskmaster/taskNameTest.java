package com.example.taskmaster;



import android.content.Context;
import android.view.View;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;

import static androidx.test.espresso.action.ViewActions.click;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class taskNameTest {
    @Rule
    public ActivityScenarioRule<MainActivity> rule2=new ActivityScenarioRule<>(MainActivity.class);
    @Test
   public void taskNameTest1() {

    onView(withId(R.id.tasksList)).perform(actionOnItemAtPosition(0, click()));

        Espresso.pressBack();
        onView(withId(R.id.tasksList)).check(matches(isDisplayed()));
        onView(withText("Fg")).check(matches(isDisplayed()));
}



}
