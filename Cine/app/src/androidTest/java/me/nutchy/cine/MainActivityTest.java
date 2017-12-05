package me.nutchy.cine;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.RatingBar;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
    public void mainActivityTest() {
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(0, R.id.tv_movieName))
                .check(matches(withText("It")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(1, R.id.tv_movieName))
                .check(matches(withText("Thor: Ragnarok")));
        onView(withRecyclerView(R.id.rc_popular_movies)
                .atPositionOnView(2, R.id.tv_movieName))
                .check(matches(withText("Justice League")));
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
                return ViewMatchers.isAssignableFrom(RatingBar.class);
            }
        };
    }
}




