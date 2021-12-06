package ca.team.safe.smart.home.it.safe.smart.home;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    @Rule
    public ActivityTestRule<LoginSep> mActivityTestRule = new ActivityTestRule<>(LoginSep.class);

    @Test
    public void loginScreenTest() {
        onView(withId(R.id.loginbtn)) // ViewMatchers - withId(R.id.german) is to
                // specify that we are looking for Button
                // with id = R.id.german
                .perform(click());

        onView(withId(R.id.buttonRegister)) // ViewMatchers - withId(R.id.german) is to
                // specify that we are looking for Button
                // with id = R.id.german
                .perform(click());
//        ViewInteraction editText = onView(
//                allOf(withId(R.id.editTextTextPersonName2), withText("emailaddress"),
//                        withParent(allOf(withId(R.id.view),
//                                withParent(withId(android.R.id.content)))),
//                        isDisplayed()));
       // editText.check(matches(withText("")));



    }
}
