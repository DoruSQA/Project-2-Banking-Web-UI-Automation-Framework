package project.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import dorrusqa.utils.CustomWaits;

public class ElementWaits extends CustomWaits{

	public static WebElement waitForVisibility(WebDriver driver, By locator) {
		
	Duration TIMEOUT = Duration.ofSeconds(Integer.parseInt(ConfigEnvReader.get("timeout")));
	WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
	
}
