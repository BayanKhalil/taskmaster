package com.example.taskmaster;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class addTaskTest {


    @Rule
    public ActivityScenarioRule<MainActivity> rule2=new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void addTask() {
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.editTextDescription)).check(matches(isDisplayed()));
    }

}
