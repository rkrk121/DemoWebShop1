package com.tricentis.genericutility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class ListenerUtility extends BaseClass implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getName();
		jLib=new JavaUtility();
		String time = jLib.getSystemTime();
		listTest.log(Status.FAIL, methodName+ " is failed");
		TakesScreenshot ts=(TakesScreenshot) driver;
		listTest.addScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64));
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshots/"+methodName+time+".png");
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
