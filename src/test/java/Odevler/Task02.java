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

public class Task02 {

   /* 2.    Scenario 2
    a.    Views->Expendable List'e tiklayin
    b.    Custom Adaptor'e tiklayin
    c.    People Names'e tiklayin ve 4 adet ismin visible oldugunu assert edin
    d.    People Names'e tekrar tiklayin ve 4 adet ismin invisible oldugunu assert edin
    e.    Fish Names'e tiklayin ve ikinci siradaki ismin Bubbles oldugunu assert edin.*/

    By lContinue = By.xpath("//*[@text='Continue']");
    By lButton1 = By.xpath("//android.widget.LinearLayout/android.widget.Button[2]");
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
    @Test
    public void test1(){

      click(xpathOfText("Views"));
      click(xpathOfText("Expandable Lists"));
      click(xpathOfText("1. Custom Adapter"));
      click(xpathOfText("People Names"));
driver.findElement(xpathOfText("Arnold")).isEnabled();
driver.findElement(xpathOfText("Barry")).isEnabled();
driver.findElement(xpathOfText("Chuck")).isEnabled();
driver.findElement(xpathOfText("David")).isEnabled();
        click(xpathOfText("People Names"));

        Boolean arnold1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Arnold")));
        Assert.assertTrue(arnold1);
        Boolean barry1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Barry")));
        Assert.assertTrue(barry1);
        Boolean chuck1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("Chuck")));
        Assert.assertTrue(chuck1);
        Boolean david1 = wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathOfText("David")));
        Assert.assertTrue(david1);

click(xpathOfText("Fish Names"));
MobileElement bubbles=driver.findElement(xpathOfText("Bubbles"));
Assert.assertEquals(bubbles.getText(),"Bubbles");



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
