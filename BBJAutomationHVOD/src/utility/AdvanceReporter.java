package utility;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class AdvanceReporter {
	
	String ScName = Utility.getScenerioName();
    
    public String ReportsPath = Utility.ReportPath;

    String DatablePath = Utility.BasePath + "\\Data Table";

    String ModulePath = Utility.BasePath + "\\Modules";

	int imgCounter;
	int stepLogCounter;
	WebDriver driver;
	SimpleDateFormat dateFormat;
	
	
	public AdvanceReporter(WebDriver driver) {
		try {
			this.driver = driver;
			imgCounter = 1;
			stepLogCounter = 1;
			dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reset Step and image counter
	public void resetStepCounter() {
		stepLogCounter = 1;
	}

	// Log text and take screenshot
	public void logWithScreenshot(String logText) throws Exception {
		String newLine = "<br /><br />";

		// Create FileName for screenshots
		String fileName = "Test -"  + imgCounter + ".jpg";

		String relativePath = "./Screenshots/" + fileName;
		String screenshotFilePath = ReportsPath + "\\Screenshots\\" + fileName;

		// Capture Screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(screenshot, new File(screenshotFilePath));

		String imgTag = "<FIGURE><img src='" + relativePath + "' height='150px' width='200px' /><FIGCAPTION>"+logText+"</FIGCAPTION></FIGURE>";

		String dateStr = dateFormat.format(Calendar.getInstance().getTime());

		Reporter.log("[" + dateStr + "]" + " | " + "Step " + stepLogCounter + " | " + logText
				+ newLine + "<a href='" + relativePath + "'>" + imgTag + "</a>" + newLine);

		imgCounter++;;
		stepLogCounter++;
	}

	// Log text
	public void log(String logText) throws Exception {
		String newLine = "<br />";
		String dateStr = dateFormat.format(Calendar.getInstance().getTime());
		Reporter.log("[" + dateStr + "]" + " | " + "Step " + stepLogCounter + " | " + logText
				+ newLine);
		stepLogCounter++;
	}

	// Log raw text as it is - Without incrementing log count
	public void rawLog(String logText) throws Exception {
		Reporter.log(logText);
	}

	// Embed image in report without increment log or image count
	public void embedImage(String fileName) throws Exception {
		String newLine = "<br />";
		String relativePath = "./Screenshots/" + fileName;
		String imgTag = "<img src='" + relativePath + "' height='150px' width='200px' />";
		String logText = "<a href='" + relativePath + "'>" + imgTag + "</a>" + newLine;
		Reporter.log(logText);
		
	}

}
