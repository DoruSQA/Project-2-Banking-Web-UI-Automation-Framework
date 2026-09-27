package project.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import project.utils.ElementWaits;

public abstract class BasePage {

	public WebDriver driver;

	// Base Parent Constructor
	public BasePage(WebDriver d) {
		this.driver = d;
	}

	// Base Page Locators
	public By pageHeading = By.xpath("//h1");
	public By shoppingCartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
	public By leftMenu = By.xpath("//aside[@data-testid='bank-sidebar']");
	public By logoutButton = By.xpath("//button[@aria-label='Logout']");

	// Base Page Actions
	public String getUrl()
	{
		return driver.getCurrentUrl();
	}
	public String getPageHeading()
	{
		return ElementWaits.waitToBeInteractable(driver,pageHeading).getText();
	}
	
	public boolean isSideMenuDisplayed()
	{
		try {
			return ElementWaits.waitForVisibility(driver, leftMenu).isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public boolean isLogoutButtonDisplayed()
	{
		try {
			return ElementWaits.waitForVisibility(driver, logoutButton).isDisplayed();
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void clickLogoutButton()
	{
		ElementWaits.waitForVisibility(driver, logoutButton).click();
	}
	
	
	public boolean isShoppingCartIconDisplayed() {
		return driver.findElement(shoppingCartIcon).isDisplayed();
	}
	
	public void clearFieldBox(By locator)
	{
		ElementWaits.waitToBeInteractable(driver, locator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(locator).sendKeys(Keys.DELETE);
		
	}
	
	
}
