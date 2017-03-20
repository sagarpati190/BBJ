package pages.staging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.Utility;

public class StagingScheduleEntires extends Utility    {
	
	By lablePlatformName = By.className("schedule-entry-platform-name");
    By byschedulingCheckBox = By.xpath("//*[@name='scheduling_ids[]']");
	By btnEdit = By.partialLinkText("Edit");
	By btnSendSequence = By.partialLinkText("Send to Sequence");
	By selectWorkflowTemplate = By.id("workflow_template_url");
    By btnSendtoSequencePage = By.name("workflows");
    By btnPublish = By.partialLinkText("Publish");
    
    
    ArrayList<String> vospPlatformChk = new ArrayList<String>();
    ArrayList<String> youviewPlatformChk = new ArrayList<String>();
    ArrayList<String> allPlatformChk = new ArrayList<String>();
    
    HashMap<String,String> PlatformVarient=new HashMap<String,String>();
    HashMap<String,String> PlatformPresentationVideoQuality=new HashMap<String,String>();  
    ArrayList<String>  vospRenditions = new ArrayList<String> ();
    ArrayList<String>  youviewRendition = new ArrayList<String> ();
	WebDriver driver;
	
	public StagingScheduleEntires(WebDriver driver){
		this.driver = driver;
	}
	
	
	public boolean setPlatformId(){
		try{
			String platformVarient=null, platformVideoQuality = null;
			if(existsElement(byschedulingCheckBox, driver)){
			List <WebElement> listschedulingCheckBoxes = driver.findElements(byschedulingCheckBox);	
			
			for(WebElement schedulingCheckBox : listschedulingCheckBoxes){
					String checkBoxValue = schedulingCheckBox.getAttribute("value");
					
					By getPlatformTitle = By.xpath("//*[@id='model-scheduling-"+checkBoxValue+"']/td[3]/div/div");
					
					if(existsElement(getPlatformTitle, driver)){
						String strSchedulingPlatformName = driver.findElement(getPlatformTitle).getAttribute("title");
						System.out.println("//*[@id='model-scheduling-"+checkBoxValue+"']/td[2]/div[3]/ul/li/span");
						if(strSchedulingPlatformName.startsWith("VOSP2")){
								vospPlatformChk.add(checkBoxValue);
								
								
							}else if (strSchedulingPlatformName.startsWith("YouView")){
								youviewPlatformChk.add(checkBoxValue);
								
							}
							allPlatformChk.add(checkBoxValue);
							String[] ArrSchedulingPlatformName = strSchedulingPlatformName.split(" > ");
							platformVarient = ArrSchedulingPlatformName[2];
							platformVideoQuality =  ArrSchedulingPlatformName[1];
							//PlatformVarient.put("112121234",TVOD);
							PlatformVarient.put(checkBoxValue,platformVarient);
							PlatformPresentationVideoQuality.put(checkBoxValue,platformVideoQuality);
						}
						
				}
			return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean setUpdatePlatformId(String Epiname){
		try{
			String platformVarient=null, platformVideoQuality = null;
			if(existsElement(byschedulingCheckBox, driver)){
			List <WebElement> listschedulingCheckBoxes = driver.findElements(byschedulingCheckBox);	
			for(WebElement schedulingCheckBox : listschedulingCheckBoxes){
					String checkBoxValue = schedulingCheckBox.getAttribute("value");
					String strEpiname =  driver.findElement(By.xpath("//*[@id='model-scheduling-"+checkBoxValue+"']/td[2]/div[1]/a")).getText();
					if(Epiname.equalsIgnoreCase(strEpiname)){
						By getPlatformTitle = By.xpath("//*[@id='model-scheduling-"+checkBoxValue+"']/td[3]/div/div");
						if(existsElement(getPlatformTitle, driver)){
							String strSchedulingPlatformName = driver.findElement(getPlatformTitle).getAttribute("title");
							System.out.println("//*[@id='model-scheduling-"+checkBoxValue+"']/td[2]/div[3]/ul/li/span");
							if(strSchedulingPlatformName.startsWith("VOSP2")){
									vospPlatformChk.add(checkBoxValue);
									
									
								}else if (strSchedulingPlatformName.startsWith("YouView")){
									youviewPlatformChk.add(checkBoxValue);
									
								}
								allPlatformChk.add(checkBoxValue);
								String[] ArrSchedulingPlatformName = strSchedulingPlatformName.split(" > ");
								platformVarient = ArrSchedulingPlatformName[2];
								platformVideoQuality =  ArrSchedulingPlatformName[1];
								//PlatformVarient.put("112121234",TVOD);
								PlatformVarient.put(checkBoxValue,platformVarient);
								PlatformPresentationVideoQuality.put(checkBoxValue,platformVideoQuality);
							}
								
					}
				
				}
			return true;	
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	public ArrayList<String> getVospRenditions(String AssetIdValue){
		try{
			System.out.println();
			
			for(int i =0 ;i< allPlatformChk.size();i++){
				if(AssetIdValue.equals("Single")){
					By ByPlatform = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[3]/div/div");
					String Platform = driver.findElement(ByPlatform).getText();
					if(Platform.startsWith("VOSP2")){
						vospRenditions.add(allPlatformChk.get(i));
					}
				}else{
					// //*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[2]/div[3]/ul/li/span
					By byAssetID = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[2]/div[2]/ul/li/span");
					String strAssetID =  driver.findElement(byAssetID).getText();
					if(AssetIdValue.equalsIgnoreCase(strAssetID)){
						By ByPlatform = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[3]/div/div");
						String Platform = driver.findElement(ByPlatform).getText();
						if(Platform.startsWith("VOSP2")){
							vospRenditions.add(allPlatformChk.get(i));
						}
					}
				}
			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return vospRenditions;	
	}
	
	public ArrayList<String>  getYouviewRenditions(String AssetIdValue){
		try{
			
			for(int i =0 ;i< allPlatformChk.size();i++){
				if(AssetIdValue.equals("Single")){
					By ByPlatform = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[3]/div/div");
					String Platform = driver.findElement(ByPlatform).getText();
					if(Platform.startsWith("YouView")){
						youviewRendition.add(allPlatformChk.get(i));
					}
				}else{
					By byAssetID = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[2]/div[2]/ul/li/span");
					String strAssetID =  driver.findElement(byAssetID).getText();
					if(AssetIdValue.equalsIgnoreCase(strAssetID)){
						By ByPlatform = By.xpath("//*[@id='model-scheduling-"+allPlatformChk.get(i)+"']/td[3]/div/div");
						String Platform = driver.findElement(ByPlatform).getText();
						if(Platform.startsWith("YouView")){
							youviewRendition.add(allPlatformChk.get(i));
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return youviewRendition;	
	}
	
	
	
	public ArrayList<String> getAllPlatformId(){
		return allPlatformChk;
	}
	
	public ArrayList<String> getVospPlatformId(){
		return vospPlatformChk;
	}
	
	public ArrayList<String> getYouviewPlatformId(){
		return youviewPlatformChk;
	}
	
	public  HashMap<String,String> getPlatformVarient(){
		return PlatformVarient;
	}
	
	public  HashMap<String,String> getPlatformPresentationVideoQuality(){
		return PlatformPresentationVideoQuality;
	}

	public boolean ValidateMessage(String value ){
		try {	
				int totalwaitTime=0;
				for(int i = 0; i < 240;i++){
					boolean errExist = false;
					String strMainError ="";
					By BypubStatus = By.xpath("//*[@id='model-scheduling-"+value+"']/td[5]/ul/li[3]/i");
					By spanErrorMessage = null;			
					if(existsElement(BypubStatus, driver)){
						driver.findElement(BypubStatus).click();
						try{
							Thread.sleep(5000);
							totalwaitTime = totalwaitTime+5;
							spanErrorMessage = By.xpath("//div[@class='popover-body']/section[2]/ul/li");
							if(existsElement(BypubStatus, driver)){
								strMainError = driver.findElement(spanErrorMessage).getText();
								System.out.println("strMainError"+strMainError);
							}else{
								driver.navigate().refresh();
								Thread.sleep(10000);
								totalwaitTime = totalwaitTime+10;
								if(existsElement(BypubStatus, driver)){
									driver.findElement(BypubStatus).click();
									totalwaitTime = totalwaitTime + 10;
									Thread.sleep(10000);
									strMainError = driver.findElement(spanErrorMessage).getText();
									System.out.println("strMainError"+strMainError);
								}
							}
						}catch(Exception e){
							driver.navigate().refresh();
							Thread.sleep(10000);
							totalwaitTime = totalwaitTime+10;
							if(existsElement(BypubStatus, driver)){
								driver.findElement(BypubStatus).click();
								Thread.sleep(10000);
								strMainError = driver.findElement(spanErrorMessage).getText();
								System.out.println("strMainError"+strMainError);
							}
						}
						if(strMainError.contains("Calculating")){
							while(true && totalwaitTime < 1800){
								driver.navigate().refresh();
								Thread.sleep(10000);
								totalwaitTime = totalwaitTime+5;
								if(existsElement(BypubStatus, driver)){
									driver.findElement(BypubStatus).click();
									Thread.sleep(10000);
									totalwaitTime = totalwaitTime + 10;
									strMainError = driver.findElement(spanErrorMessage).getText();
									System.out.println("strMainError"+strMainError);
								}
								if(! strMainError.contains("Calculating")){
									break;
								}
							}
						}
						By ByerrorMessage = By.xpath("//li[@class='model-error']");
						if(existsElement(ByerrorMessage, driver)){
							List <WebElement> listErrorMessages = driver.findElements(ByerrorMessage);
							if(existsElement(ByerrorMessage, driver) && (strMainError.contains("Invalid")) ){
								for(WebElement listErrorMessage :listErrorMessages){
									String errorMessage = listErrorMessage.getText();
									System.out.println("errorMessage:"+errorMessage);
									if(errorMessage.contains("Presentation Video Quality must be present")){
										errExist = true;
									}
							}
						}
						if(!errExist){
							return true;			
						}
						Thread.sleep(10000);
						totalwaitTime = totalwaitTime+10;
						driver.findElement(BypubStatus).click();
						Thread.sleep(1000);
						totalwaitTime = totalwaitTime+1;
						System.out.println("Total time:" +totalwaitTime);
						}
						
						
					}else{
						return false;
					}
					
				
			}
		return false;
		}catch (Exception e) {
			e.printStackTrace();
					return false;			
		}
		
	}
	
	public boolean clickCheckBox(String value){
		try {	
		
			By chkSchedule = By.id("scheduling_check_box_"+value);
			if(existsElement(chkSchedule, driver)){
				driver.findElement(chkSchedule).click();
				return true;
			}
			
			return false;
		}catch (Exception e) {
		e.printStackTrace();
				return false;			
	}
	}
	
	public String getEpiName(String value){
		try {	
		
			By ByEpiName = By.xpath("//*[@id='model-scheduling-"+value+"']/td[2]/div[1]/a");
			if(existsElement(ByEpiName, driver)){
				return driver.findElement(ByEpiName).getText();
			}
			
			return "";
		}catch (Exception e) {
		e.printStackTrace();
				return "";			
	}
	}
	
	public boolean clickEditbtn(){
		
		try {	
		
			if(existsElement(btnEdit , driver)){
				driver.findElement(btnEdit).click();
				return true;
		}
		return false;
		}catch (Exception e) {
		e.printStackTrace();
				return false;			
		}			
	}	
	
public boolean clickSendtoSequencebtn(){
		
		try {	
		
			if(existsElement(btnSendSequence,driver)){
				driver.findElement(btnSendSequence).click();
				return true;
		}
		return false;
		}catch (Exception e) {
		e.printStackTrace();
				return false;			
		}			
	}

public boolean selectWorkflowTemplate(String value){
	try {	
		
		if(existsElement(selectWorkflowTemplate,driver)){
			Select workFlowTemplate =new Select(driver.findElement(selectWorkflowTemplate));
			workFlowTemplate.selectByVisibleText(value);
			return true;
	}
	return false;
	}catch (Exception e) {
			e.printStackTrace();
			return false;			
	}	
	
}

public boolean clickSendtoSequencebtnPage(){
	
	try {	
	
		if(existsElement(btnSendtoSequencePage,driver)){
			driver.findElement(btnSendtoSequencePage).click();
			return true;
	}
	return false;
	}catch (Exception e) {
	e.printStackTrace();
			return false;			
	}			
}

public boolean clickPublishButton(){
	
	try {	
	
		if(existsElement(btnPublish,driver)){
			driver.findElement(btnPublish).click();
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();	
			alert.accept();	
			Thread.sleep(5000); 
			driver.navigate().refresh();
			Thread.sleep(2000); 
			return true;
	}
	return false;
	}catch (Exception e) {
	e.printStackTrace();
			return false;			
	}			
}

public boolean clickContentLink(String value){
	try {	
		By byAssetlink = By.xpath("//a[text()='"+value+"']");
		
		if(existsElement(byAssetlink,driver)){
			if(driver.findElement(byAssetlink).isEnabled()){
				Thread.sleep(2000);
				driver.findElement(byAssetlink).click();
				return true;
			}
			
	}
	return false;
	}catch (Exception e) {
	e.printStackTrace();
			return false;			
	}	
	
}

public boolean ValidateMamMessage(String value ){
	try {	
		
			By ByMAMStatus = By.xpath("//*[@id='model-scheduling-"+value+"']/td[5]/ul/li[2]/i");
			By spanMAMMessage = null;
			String strMAMmessage = null;
			if(existsElement(ByMAMStatus, driver)){
					driver.findElement(ByMAMStatus).click();
					try{
						Thread.sleep(5000);
						spanMAMMessage = By.xpath("//li[@class='task-pending']");
						strMAMmessage = driver.findElement(spanMAMMessage).getText();
						System.out.println("strMAMmessage"+strMAMmessage);
					}catch (Exception e){
						driver.navigate().refresh();
						Thread.sleep(2000);
						driver.findElement(ByMAMStatus).click();
						strMAMmessage = driver.findElement(spanMAMMessage).getText();
					}
					if((strMAMmessage.contains("MAM Check-in"))){
						return true;
					}
					
				}else{
					return false;
				}
		return false;
	}catch (Exception e) {
		e.printStackTrace();
				return false;			
	}
	
}

public String getAssetID(String episodeName){
	
	return "";
}


public boolean uploadFile(String assetID, String mAMFolder) {
	//copy file conventional way using Stream
	try{
		
	
	    long start = System.nanoTime();
	    File srcFile2 = new File(UtilityPath+"//2minute_HD_2CH_50Mbps.mxf");
		File destDir2 = new File(ReportPath+"//"+assetID+".mxf");
		if (!destDir2.exists()){
			FileUtils.copyFile( srcFile2,destDir2);
		}
	    System.out.println("Time taken by Stream Copy = "+(System.nanoTime()-start));
	    System.out.println("in the air**********");
	    
	    /* Create text File */
		File UploadTextfile = new File(ReportPath+"//Uploadvideo_"+assetID+".txt");
		if (!UploadTextfile.exists()){
			if (UploadTextfile.createNewFile()){
				System.out.println("Upload Textfile is created!");
			}
			else {
				System.out.println("Failed to create Upload Textfile!");
			}
		}
		else{
			System.out.println("Upload Textfile already exists");
		}
	
		FileWriter fwu = new FileWriter(UploadTextfile.getAbsoluteFile());
		BufferedWriter bwu = new BufferedWriter(fwu);
		
		bwu.write(" cd e2e_transcode/"+mAMFolder+"\n");
		bwu.write(" put "+ReportPath+"\\"+assetID+".mxf");
		bwu.close();
	  
		
	    /* Create Batch File */
		File UploadBatchfile = new File(ReportPath+"//Uploadvideo_"+assetID+".bat");
		if (!UploadBatchfile.exists()){
			if (UploadBatchfile.createNewFile()){
				System.out.println("Upload Textfile is created!");
			}
			else {
				System.out.println("Failed to create Upload Textfile!");
			}
		}
		else{
			System.out.println("Upload Textfile already exists");
		}
	
		fwu = new FileWriter(UploadBatchfile.getAbsoluteFile());
		bwu = new BufferedWriter(fwu);
		
		bwu.write(UtilityPath+"\\"+"psftp.exe -l mame2etest_staging -pw Ch4ng9m3 217.15.66.31 -b \""+ReportPath+"\\Uploadvideo_"+assetID+".txt\"");
		bwu.close();
		 /* Create Batch File */
		
		
		Process process=Runtime.getRuntime().exec("cmd /c start /min \"\" \"" +ReportPath+"\\Uploadvideo_"+assetID+".bat\"");
		final int exitVal = process.waitFor();
		return true;
	}catch(Exception e){
		return false;
	}
}
}	

