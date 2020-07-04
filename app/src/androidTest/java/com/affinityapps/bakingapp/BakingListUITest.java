package com.affinityapps.bakingapp;

import android.content.Context;

import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.affinityapps.bakingapp.ingredients.IngredientsListFragment;
import com.affinityapps.bakingapp.master.RecipeListActivity;
import com.affinityapps.bakingapp.recipes.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BakingListUITest {
    @Rule
    public ActivityTestRule activityRule =
            new ActivityTestRule(MainActivity.class);

    @Test
    public void recipeCardListTest() {
        onView(withId(R.id.recipe_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void recipeListActivityTest() {
        onView(withId(R.id.recipe_recyclerview)).perform(click());
        onView(withId(R.id.recipe_master_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void ingredientsListFragmentTest() {
        onView(withId(R.id.recipe_recyclerview)).perform(click());
        onView(allOf(withId(R.id.recipe_ingredients_text), withText("Recipe Ingredients"))).perform(click());
        onView(withId(R.id.ingredients_recyclerview)).check(matches(isDisplayed()));
    }
}
