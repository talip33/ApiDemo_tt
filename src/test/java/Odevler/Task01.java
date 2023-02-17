package Odevler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.App;
import utils.Device;
import utils.Driver;

import java.text.MessageFormat;
import java.time.Duration;

public class Task01 {

    By lContinue = By.xpath("//*[@text='Continue']");
    By lButton1 = By.xpath("//android.widget.LinearLayout/android.widget.Button[2]");


    By lInputBox=By.id("com.touchboarder.android.api.demos:id/edit");
    By lOffButton1=By.id("com.touchboarder.android.api.demos:id/toggle1");
    By lOffButton2=By.id("com.touchboarder.android.api.demos:id/toggle2");
    By lSelectBox=By.id("com.touchboarder.android.api.demos:id/spinner1");

    String textXpath = "//*[@text=\"{0}\"]";

    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    @BeforeTest
    public void beforeTest() {
        Driver.runAppium();
        driver = Driver.getDriver(Device.PIXEL_33, App.APIDEMO);
        wait = new WebDriverWait(driver, 10);
        waitForVisibility(lContinue);
        click(lContinue);
        waitForVisibility(lButton1);
        click(lButton1);

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
        Driver.stopAppium();
    }

    /*
    1.    Scenario 1
        a.    Views->Controls'a tiklayin
        b.    Light Theme'e tiklayin
        c.    Inputbox'a "Controls" metnini send ediniz
        d.    Checkbox1'e tiklayin ve checked oldugunu assert edin
        e.    RadioButton1'e tiklayin  ve checked oldugunu assert edin
        f.    Star'a  tiklayin  ve checked oldugunu assert edin
        g.    Ilk button'a  tiklayin ve textinin ON, statüsünün checked oldugunu assert edin
        h.    Ikinci button'un textinin OFF, statüsünün unchecked oldugunu assert edin
        i.    Selectbox'dan Mars secenegini seciniz.
     */
    @Test
    public void test1() {
        click(xpathOfText("Views"));
        click(xpathOfText("Controls"));
        click(xpathOfText("01. Light Theme"));

        MobileElement inputBox = driver.findElement(lInputBox);
        inputBox.sendKeys("Controls");

        MobileElement checkBox = driver.findElement(xpathOfText("Checkbox 1"));
        click(xpathOfText("Checkbox 1"));
        // Assert.assertTrue(checkBox.isSelected());
        Assert.assertEquals(checkBox.getAttribute("checked"),"true");
        Assert.assertTrue(Boolean.valueOf(checkBox.getAttribute("checked")));
        Assert.assertTrue(Boolean.parseBoolean(checkBox.getAttribute("checked")));

        MobileElement radioBox = driver.findElement(xpathOfText("RadioButton 1"));
        click(xpathOfText("RadioButton 1"));
        //Assert.assertTrue(radioBox.isSelected());
        //Assert.assertTrue(Boolean.valueOf(radioBox.getAttribute("checked")));
        Assert.assertTrue(Boolean.parseBoolean(radioBox.getAttribute("checked")));

        MobileElement star = driver.findElement(xpathOfText("Star"));
        click(xpathOfText("Star"));
        // Assert.assertTrue(star.isSelected());
        Assert.assertTrue(Boolean.parseBoolean(star.getAttribute("checked")));
        swipeV(.5, .2);
        MobileElement offButton1 = driver.findElement(lOffButton1);
        //kapaliButton.click();
       waitForVisibility(lOffButton1);

        click(lOffButton1);
        String expectedText="ON";
        Assert.assertEquals(offButton1.getText(),expectedText);
        //Assert.assertTrue(offbutton1.isSelected());
        Assert.assertTrue(Boolean.parseBoolean(offButton1.getAttribute("checked")));

        MobileElement offButton2 = driver.findElement(lOffButton2);
        String expectedText2="OFF";
        Assert.assertEquals(offButton2.getText(),expectedText2);
        //Assert.assertFalse(offButton2.isSelected());-->çalışıyor
        Assert.assertFalse(Boolean.parseBoolean(offButton2.getAttribute("checked")));



        MobileElement selectBox = driver.findElement(lSelectBox);
        //selectBox.click();
        click(lSelectBox);
        click(xpathOfText("Mars"));
    }


    public void click(By locator) {
        driver.findElement(locator).click();
    }

    By xpathOfText(String... text) {
        return By.xpath(MessageFormat.format(textXpath, text));
    }

    void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void swipeV(double startPoint, double endPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w / 2, (int) (h * startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w / 2, (int) (h * endPoint)))
                .release()
                .perform();
    }

    public void swipeUntil(By locator, double start, double end) {
        while (driver.findElements(locator).size() <= 0)
            swipeV(start, end);
    }


}

