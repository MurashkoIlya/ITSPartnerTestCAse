package by.scar.itspartnertestcase;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest{

    private static final String APPLICATION_PACKAGE = "by.scar.itspartnertestcase";
    private static final int LAUNCH_TIMEOUT = 5000;
    private final String EDITTEXT_CLASS_NAME = "android.widget.EditText";
    private final String BUTTON_CLASS_NAME = "android.widget.Button";
    private final String TRUE_LOGIN_PASSWORD = "test";
    private final String FALSE_LOGIN_PASSWORD = "not test";
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String BACK = "BACK";
    private final String BACK_BUTTON_RESOURCE_ID = "by.scar.itspartnertestcase:id/back_button";

    private UiDevice device;

    private UiObject loginField;
    private UiObject passwordField;
    private UiObject backButton;

    @Before
    public void startAppFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        final String launcherPackage = device.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        Context context =
                InstrumentationRegistry.getInstrumentation().getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(APPLICATION_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(APPLICATION_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void firstTestCase() throws UiObjectNotFoundException {
        initFields();
        setFields(TRUE_LOGIN_PASSWORD, TRUE_LOGIN_PASSWORD);
        checkSecondScreen();
    }

    @Test
    public void secondTestCase() throws UiObjectNotFoundException {
        initFields();
        setFields(FALSE_LOGIN_PASSWORD, FALSE_LOGIN_PASSWORD);
        checkFirstScreen(FALSE_LOGIN_PASSWORD, FALSE_LOGIN_PASSWORD);
    }

    @Test
    public void thirdTestCase() throws UiObjectNotFoundException {
        initFields();
        setFields(TRUE_LOGIN_PASSWORD, TRUE_LOGIN_PASSWORD);
        checkSecondScreen();
        initBackButton();
        secondScreenButtonClick();
        checkFirstScreen(LOGIN, PASSWORD);
    }

    public void initFields(){
        loginField = getUiObjectByDescription(EDITTEXT_CLASS_NAME, LOGIN);
        passwordField = getUiObjectByDescription(EDITTEXT_CLASS_NAME, PASSWORD);
    }

    public void initBackButton(){
        backButton = getUiObjectByRecourseId(EDITTEXT_CLASS_NAME, BACK_BUTTON_RESOURCE_ID);
    }

    public void setFields(String login, String password) throws UiObjectNotFoundException {
        loginField.setText(login);
        passwordField.setText(password);
    }

    public void secondScreenButtonClick() throws UiObjectNotFoundException {
        backButton.clickAndWaitForNewWindow();
    }

    public void checkFirstScreen(String login, String password) {
        assertTrue(getUiObjectByDescription(EDITTEXT_CLASS_NAME, login).exists());
        assertTrue(getUiObjectByDescription(EDITTEXT_CLASS_NAME, password).exists());
    }

    public void checkSecondScreen() {
        assertTrue(getUiObjectByDescription(BUTTON_CLASS_NAME, BACK).exists());
    }

    public UiObject getUiObjectByDescription(String className, String description){
        return device.findObject(new UiSelector().className(className).text(description));
    }

    public UiObject getUiObjectByRecourseId(String className, String resourceId){
        return device.findObject(new UiSelector().className(className).resourceId(resourceId));
    }
}
