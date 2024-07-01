package com.tricentis.genericutility;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	public Actions act;
	
	public WebDriverUtility(WebDriver driver) {
		act=new Actions(driver);
	}
	
	public void mouseHover(WebElement element) {
		act.moveToElement(element).perform();
	}
	
	public void dragAndDrop(WebElement from,WebElement to) {
		act.dragAndDrop(from, to).perform();
	}
	
	public void clickAndHold(WebElement element) {
		act.clickAndHold(element).perform();
	}
	
	public void switchToWindowByUrl(WebDriver driver,String expectedUrl) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String id:allWindowIds) {
			String actualUrl = driver.switchTo().window(id).getCurrentUrl();
			if (actualUrl.contains(expectedUrl)) {
				break;
			}
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver,String expectedTitle) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for(String id:allWindowIds) {
			String actualTitle = driver.switchTo().window(id).getTitle();
			if (actualTitle.contains(expectedTitle)) {
				break;
			}
		}
	}
	public WebDriver switchToFrame(WebDriver driver,int index) {
		return driver.switchTo().frame(index);
	}
	
	public WebDriver switchToFrame(WebDriver driver,String nameOrId) {
		return driver.switchTo().frame(nameOrId);
	}
	
	public WebDriver switchToFrame(WebDriver driver,WebElement frameElement) {
		return driver.switchTo().frame(frameElement);
	}
	
	public WebDriver switchBackToDefault(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}
	
	public void selectByIndex(WebElement dropDownElement,int index) {
		Select list=new Select(dropDownElement);
		list.selectByIndex(index);
	}
	
	public void selectByValue(WebElement dropDownElement,String value) {
		Select list=new Select(dropDownElement);
		list.selectByValue(value);
	}
	
	public Alert switchToAlert(WebDriver driver) {
		return driver.switchTo().alert();
	}
	
}
