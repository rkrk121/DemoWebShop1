package com.tricentis.shoppingcart;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.tricentis.genericutility.BaseClass;
import com.tricentis.genericutility.ListenerUtility;

@Listeners(ListenerUtility.class)
public class TC_DWS_003_Test extends BaseClass{
	@Test
	public void addProductToCart() {
		driver.findElement(By.xpath("//a[text()='14.1-inch Laptop']/../..//input[@value='Add to cart']")).click();
		boolean msgStatus = homePage.getConfirmMessage().isDisplayed();
		Assert.assertEquals(msgStatus, true,"product failed to add");
		test.log(Status.PASS, "Product has been added to cart");
		wait.until(ExpectedConditions.invisibilityOf(homePage.getConfirmMessage()));
	}
}
