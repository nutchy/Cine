package me.nutchy.cine;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.Gravity;
import android.view.View;
import android.widget.RatingBar;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void popularResultTest() {
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.tv_movieName))
                .check(matches(withText("It")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.tv_movieName))
                .check(matches(withText("Thor: Ragnarok")));
    }

    @Test
    public void movieIt(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.tv_movieName))
                .check(matches(withText("It")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.iV_poster)).perform(click());
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText("It")).check(matches(withParent(withId(R.id.toolbar))));
    }

    @Test
    public void movieThor(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.tv_movieName))
                .check(matches(withText("Thor: Ragnarok")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.iV_poster)).perform(click());
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText("Thor: Ragnarok")).check(matches(withParent(withId(R.id.toolbar))));
    }

    @Test
    public void checkAppName(){
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withText(R.string.app_name)).check(matches(withParent(withId(R.id.toolbar))));
    }

    @Test
    public void voteIt_5_Star(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.tv_movieName))
                .check(matches(withText("It")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.iV_poster)).perform(click());
        onView(withId(R.id.youStar)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.rating_bar)).perform(setProgress(5));
        onView(withId(R.id.btn_rating)).perform(click());
        onView(withId(R.id.tv_your_rate)).check(matches(withText("5/10")));
    }

    @Test
    public void voteThor_7_Star(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.tv_movieName))
                .check(matches(withText("Thor: Ragnarok")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.iV_poster)).perform(click());
        onView(withId(R.id.youStar)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.rating_bar)).perform(setProgress(10));
        onView(withId(R.id.btn_rating)).perform(click());
        onView(withId(R.id.tv_your_rate)).check(matches(withText("10/10")));
    }

    @Test
    public void favoriteBtnSingleTapTest(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.iV_poster)).perform(click());
        onView(withId(R.id.menu_favorite)).perform(click());
    }

    @Test
    public void favoriteBtnDoubleTapTest(){
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.iV_poster)).perform(click());
        onView(withId(R.id.menu_favorite)).perform(click());
        onView(withId(R.id.menu_favorite)).perform(click());
    }

    @Test
    public void displayFavoriteList(){
        // Open Drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        // Show Content
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_fav));
        onView(withId(R.id.tv_fav_title)).check(matches(isDisplayed()));
    }

    public static ViewAction setProgress(final int progress) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                ((RatingBar) view).setRating(progress);
            }

            @Override
            public String getDescription() {
                return "Set a progress";
            }

            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(RatingBar.class);
            }
        };
    }
}




