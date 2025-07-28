package MobileTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class MyTestCases {
	AndroidDriver driver;

	DesiredCapabilities caps = new DesiredCapabilities();

	@BeforeTest
	public void mySetup() {
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:deviceName", "PSUT");

		File myapp = new File("src/myApplication/calculator.apk");

		caps.setCapability("appium:app", myapp.getAbsolutePath());

	}

	@BeforeMethod
	public void beforeEachTest() throws MalformedURLException {
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

	}

	@Test(enabled = false)
	public void myFirsttest() {

		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();

		driver.findElement(By.id("com.google.android.calculator:id/digit_6")).click();

		String theResults = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();

		System.out.println(theResults);

		Assert.assertEquals(theResults, "54");

	}

	@Test(enabled = false)
	public void pressOnAllButtons() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {
			allButtons.get(i).click();
		}
	}

	@Test(enabled = false)
	public void pressOnAllNumbers() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit")) {
				allButtons.get(i).click();

			}
		}

		String actualResutl = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();

		Assert.assertEquals(actualResutl, "7894561230");
	}

	@Test(enabled = false)
	public void pressonEvenNumbers() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++) {

			if (allButtons.get(i).getDomAttribute("resource-id").contains("8")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("6")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("4")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("2")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("0")) {
				allButtons.get(i).click();

			}
		}

	}
	
	
	@Test(enabled = false)
	public void pressonEvenNumbers2() {

		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for(int i = 0 ; i < allButtons.size();i++) {
			;
			System.out.println(allButtons.get(i).getDomAttribute("content-desc"));
		}
		}
	
	@Test
	public void pressOnEvenNumbersOnly() {
	    List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

	    for (WebElement button : allButtons) {
	        String label = button.getDomAttribute("content-desc");

	        // Check if it's a digit and also an even number
	        if (label != null && label.matches("\\d")) {
	            int num = Integer.parseInt(label);
	            if (num % 2 == 0) {
	                System.out.println("Clicking even number: " + num);
	                button.click();
	            }
	        }
	    }
	}


	
}