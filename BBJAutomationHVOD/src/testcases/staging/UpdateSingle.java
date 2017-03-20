package testcases.staging;

import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.staging.StagingAddAsset;
import pages.staging.StagingAddtoSchedule;
import pages.staging.StagingCatalog;
import pages.staging.StagingEditSchedule;
import pages.staging.StagingImages;
import pages.staging.StagingLogin;
import pages.staging.StagingMISReporting;
import pages.staging.StagingMain;
import pages.staging.StagingScheduleEntires;
import pages.staging.StagingTitle;
import utility.AdvanceReporter;
import utility.Utility;

public class UpdateSingle extends Utility {

	WebDriver driver;
	AdvanceReporter reporter;
	Utility util;
	boolean result;
	String strContentName = null;
	StagingCatalog catalog;
	StagingMain main;
	StagingAddAsset asset;
	StagingTitle title;
	StagingScheduleEntires scheduleEntry;
	ArrayList<String> youviewPlatform,vospPlatform;
	private ArrayList<String> AllPlatform, vospPlatformChk;
	StagingEditSchedule editSchedule;
	private HashMap<String, String> PlatformVarient,
			PlatformPresentationVideoQuality = new HashMap<String, String>();

	@BeforeTest
	public void testsetup() {
		
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile myprofile = profile.getProfile("default");
		  
		driver = new FirefoxDriver(myprofile);
		reporter = new AdvanceReporter(driver);
		result = false;
		util = new Utility();
		catalog = new StagingCatalog(driver);
		main = new StagingMain(driver);
		scheduleEntry = new StagingScheduleEntires(driver);
		editSchedule = new StagingEditSchedule(driver);
		asset = new StagingAddAsset(driver);
		title = new StagingTitle(driver);

	}

	@Test
	public void UpdateSingleContent() {

		try {

			
			 //--------------------------Login Start------------------------------
			

			String Flag = xlRead("Funtionality");
			String updateFlg = xlRead("UpdateFlag");

			StagingLogin login = new StagingLogin(driver);

			result = login.navigatetoStaging();
			Assert.assertTrue(result, "Failed to launch Staging instance.");
			reporter.log("Staging Launched Successfully");

			 String strUserName=xlreadint(1, 1);
		  	  result = login.setUsername(strUserName);
		  	  Assert.assertTrue(result, "Failed to Set Username"); 
		  	  reporter.log("Username Set Successfully");
		  	 
		  	  String strPassword=xlreadint(2, 1);
		  	  result = login.setPassword(strPassword);
		  	  Assert.assertTrue(result, "Failed to Set Password");
		  	  reporter.log("Password Set Successfully");

			reporter.logWithScreenshot("Submit Button Clicked Successfully");
			result = login.clickSubmit();
			Assert.assertTrue(result, "Failed to Click Submit Button");
			System.out.println("User Successfuly logged in");

			
			//--------------------------Login End-----------------------------------
			

			
			 // -------------------------- Search the content-----------------------
			 
			strContentName = util.xlRead("AssetTitle");

			result = catalog.setSerchText(strContentName);
			Assert.assertTrue(result, "Failed to search the content name");
			reporter.logWithScreenshot("Content Successfully searched");

			result = catalog.clickSearchedContent(strContentName);
			Assert.assertTrue(result, "Searched content could not be clicked");

			
			 //-------------------------- Content Search End-------------------------
			
			/*---------------Go to Respective Page for Update------------------*/
			
			if(updateFlg.equalsIgnoreCase("Price")||updateFlg.equalsIgnoreCase("Placement")||updateFlg.equalsIgnoreCase("Subscription")||updateFlg.equalsIgnoreCase("ExpireAll")) {
				result = main.clickNavLink("Schedule");
				Assert.assertTrue(result, "Failed to Click Schedule Link");
				reporter.log("Schedule Link Clicked Successfully");
				
				result =  main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to Click Shedule Page Button"); 
			   	reporter.log("Shedule Page Button Clicked Successfully");
				
			   	result = scheduleEntry.setPlatformId();
				Assert.assertTrue(result, "Platform Entries not Available");
				reporter.log("Platform ID's Collected Successfully");

				Thread.sleep(5000);
			   	
				AllPlatform = scheduleEntry.getAllPlatformId();
			   	for(int i=0;i<AllPlatform.size();i++){
			    	Thread.sleep(2000);
			   	
				   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
				   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
				   	reporter.log("Checkbox clicked succeessfully");		   	
				   	
				   	result = scheduleEntry.clickEditbtn();
				   	Assert.assertTrue(result, "Failed to click Edit Button"); 
				   	reporter.log("Edit Button clicked succeessfully");
				   	
				    //----------------Price------------------
				   	
				   	if (updateFlg.equalsIgnoreCase("Price")){
				   		
				   		PlatformVarient = scheduleEntry.getPlatformVarient();
						PlatformPresentationVideoQuality = scheduleEntry.getPlatformPresentationVideoQuality();
						String strPrice = null;
						if (PlatformVarient.get(AllPlatform.get(i)).equalsIgnoreCase("TVOD")) {
							if (xlRead("TVODVarient").contains(";")) {
								String[] TVODPricearr = xlRead("TVODPrice").split(";");
								if (PlatformPresentationVideoQuality.get(AllPlatform.get(i)).equals("SD")) {
									strPrice = TVODPricearr[0];
								} else {
									strPrice = TVODPricearr[1];
								}
							} else {
								strPrice = xlRead("TVODPrice");
							}
						} else if (PlatformVarient.get(AllPlatform.get(i)).equals("EST")) {
							if (xlRead("ESTVarient").contains(";")) {
								String[] strPricearr = xlRead("ESTPrice").split(";");
								if (PlatformPresentationVideoQuality.get(AllPlatform.get(i)).equals("SD")) {
									strPrice = strPricearr[0];
								} else {
									strPrice = strPricearr[1];
								}
							} else {
								strPrice = xlRead("ESTPrice");
							}
						}
						System.out.println("strPrice------>"+strPrice);
						result = editSchedule.setPrice(strPrice,AllPlatform.get(i), Flag);
						Assert.assertTrue(result, "Failed to Select Price");
						reporter.log("Price Set succeessfully");
					
				   	}
				   	
				 //----------------placement------------------
				   	
				   	else if (updateFlg.equalsIgnoreCase("Placement")){
				   		//Checking placemnt
				   		String strPlacement = xlRead("Title_Placement");
				   		result = editSchedule.setPlacement(strPlacement, AllPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to Select Placement"); 
					   	reporter.log("Contract Set succeessfully");
				   		
				   	}
				 //----------------Subscription---------------
				   	
				   	else if (updateFlg.equalsIgnoreCase("Subscription")){

						String strSubs = xlRead("Product_Subscription");
						result = editSchedule.setSubscriptions(strSubs,AllPlatform.get(i), Flag);
						Assert.assertTrue(result,"Unable to select the subscriptions");
						reporter.logWithScreenshot("SVOD subscription changed successfully");
					
				   		
				   	}
				  //----------------ExpireAll---------------
				   	
				   	else if (updateFlg.contains("Expire")){
				   		
				   		StagingEditSchedule EditSchedule = new StagingEditSchedule(driver); 
				   		String strEndingDate = getFutureTime(2);
				   		
				   		result = EditSchedule.setExpiryDate(strEndingDate,AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
					   	reporter.log("Ending Date Entered Successfully");
				   	}
				   	
				   	
				 //  	result = editSchedule.clickSubmit();
					Assert.assertTrue(result, "Failed to click Update Button");
					reporter.log("Update button clicked succeessfully");					
					
			    }
			}

			// Change the rating of the single content

			else if (updateFlg.equalsIgnoreCase("Rating")) {
				result = main.clickNavLink("Assets");
				Assert.assertTrue(result, "Failed to Click Asset Link");
				reporter.log("Title Link Clicked Successfully");

				result = main.editAsset();
				Assert.assertTrue(result, "Failed to click edit button");
				reporter.log("Edit link clicked successfully");

				String strRating = util.xlRead("Asset_rating");
				result = asset.setAssetRating(strRating);
				Assert.assertTrue(result, "Failed to change rating");
				reporter.log("Rating changed successfully");

				result = asset.clickUpdateButton();
				Assert.assertTrue(result, "Failed to Click Update Button");
				reporter.log("Button Clicked Successfully");
				
				result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule link Clicked Successfully");
		
			   	
			   	result = main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to click submit button"); 
			   	reporter.log("Submit button clicked Successfully");	
			}

			else if (updateFlg.equalsIgnoreCase("Metadata")) {
				result = main.clickNavLink("Title");
				Assert.assertTrue(result, "Failed to Click Title Link");
				reporter.log("Title	 Link Clicked Successfully");

				result = main.clickEditMetadata();
				Assert.assertTrue(result, "Failed to Click Metadata Button");
				reporter.log("Metadata Button Clicked Successfully");

				String strTitleShort = util.xlRead("Title_ShortDesc");
				result = title.setTitleShortDesc(strTitleShort);
				Assert.assertTrue(result, "Failed to Enter Short Description");
				reporter.log("Short Description Entered Successfully");

				String strTitleMedium = util.xlRead("Title_MediumDesc");
				result = title.setTitleMediumDesc(strTitleMedium);
				Assert.assertTrue(result, "Failed to Enter Medium Description");
				reporter.log("Medium Description Entered Successfully");

				String strTitleLong = util.xlRead("Title_LongDesc");
				result = title.setTitleLongDesc(strTitleLong);
				Assert.assertTrue(result, "Failed to Enter Long Description");
				reporter.log("Long Description Entered Successfully");

				 String strTitleYear = xlRead("Title_ReleaseYr");
			   	 System.out.println("Title Year:"+strTitleYear);
			  	 result = title.setTitleYear(strTitleYear);
			  	 Assert.assertTrue(result, "Failed to Enter Title Year"); 
			  	 reporter.log("Title Year Entered Successfully");
			  	  
				result = title.clickSubmit();
				Assert.assertTrue(result, "Failed to click on Submit Button");
				reporter.log("Submit Button Clicked Successfully");
				
				result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule link Clicked Successfully");
		
			   	
			   	result = main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to click submit button"); 
			   	reporter.log("Submit button clicked Successfully");	

			}
			
			else if (updateFlg.equalsIgnoreCase("Image")) {
				String[][] imagesArr = null;
				result = main.clickNavLink("Images");
			   	Assert.assertTrue(result, "Failed to Click Image Link"); 
			   	reporter.log("Title	Image Clicked Successfully");
			   	StagingImages images = new StagingImages(driver);
			   	
			   
			    /*------------------------Images Page Start ------------------*/	
			   	String Scheduling_Plaforms = null;
			   	String strVOSP_Version = util.xlRead("VOSP_Version");
			   	if(strVOSP_Version.equalsIgnoreCase("2.1")){
			   		Scheduling_Plaforms = util.xlRead("Scheduling_Plaforms");
			   		
			   		if(Scheduling_Plaforms.equalsIgnoreCase("both")){
			   			imagesArr = new String [2][4];
			   			imagesArr[0][0]= "SinglePackshotupdate.png";
			   			imagesArr[0][1]= "packshotImageUpdate.png";
			   			imagesArr[0][2]= "Packshot";
			   			//imagesArr[0][3]= "VOSP2 > ";
			   			
			   			imagesArr[1][0]= "Hiresupdate.png";
			   			imagesArr[1][1]= "Hiresupdate.png";
			   			imagesArr[1][2]= "HiRes Packshot";	
			   			//imagesArr[1][3]= "YouView > ";
			   			
			   		}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
			   			imagesArr = new String [1][4];
			   			imagesArr[0][0]= "SampleImg.jpg";
			   			imagesArr[0][1]= "packshotImage.jpg";
			   			imagesArr[0][2]= "Packshot";
			   			//imagesArr[0][3]= "VOSP2 > ";
			   		}
			   	}
			   	
			   	for(int i=0; i<imagesArr.length;i++){
		   			result = images.editImage(); 
		   			Assert.assertTrue(result, "Failed to Edit Image"); 
		   		   	reporter.log(imagesArr[i][1] + " Image Added Successfully");
		   		   	
		   			result = images.replaceImage(imagesArr[i][0]);
		   			Assert.assertTrue(result, "Failed to replace"); 
		   		   	reporter.log(imagesArr[i][1] + " Image replaced Successfully");
		   		   	
		   		   	result = images.clickSubmit();
		   			Assert.assertTrue(result, "Failed to Click Update Buton"); 
		   		   	reporter.log("Image update Button clicked Successfully");
			   	}
			   	
			   	result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule link Clicked Successfully");
		
			   	
			   	result = main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to click submit button"); 
			   	reporter.log("Submit button clicked Successfully");	
			}else if(updateFlg.equalsIgnoreCase("AddSchedule")){
				
				result = main.clickNavLink("Images");
			   	Assert.assertTrue(result, "Failed to Click Image Link"); 
			   	reporter.log("Title	Image Clicked Successfully");
			   	StagingImages images = new StagingImages(driver);
			   	
			   
			    /*------------------------Images Page Start ------------------*/	
			   	String Scheduling_Plaforms = null;
			   	String[][] imagesArr = null;
			   	
			   	Scheduling_Plaforms = xlRead("Scheduling_Plaforms");
			   	if(Scheduling_Plaforms.equalsIgnoreCase("both")){
			   		imagesArr = new String [2][4];
			   		imagesArr[0][0]= "SampleImg.jpg";
			   		imagesArr[0][1]= "packshotImage.jpg";
			   		imagesArr[0][2]= "Packshot";
			   		imagesArr[0][3]= "VOSP2 > ";
			   		
			   		imagesArr[1][0]= "HiresImage.jpg";
			   		imagesArr[1][1]= "HiresImage.jpg";
			   		imagesArr[1][2]= "HiRes Packshot";	
			   		imagesArr[1][3]= "YouView > ";
			   			
			   	}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
			   		imagesArr = new String [1][4];
			   		imagesArr[0][0]= "SampleImg.jpg";
			   		imagesArr[0][1]= "packshotImage.jpg";
			   		imagesArr[0][2]= "Packshot";
			   		imagesArr[0][3]= "VOSP2 > ";
			   	}else if(Scheduling_Plaforms.equalsIgnoreCase("Youview")){
			   		imagesArr = new String [1][4];
			   		imagesArr[0][0]= "HiresImage.jpg";
			   		imagesArr[0][1]= "HiresImage.jpg";
			   		imagesArr[0][2]= "HiRes Packshot";	
			   		imagesArr[0][3]= "YouView > ";
			   	}
			   		
			   	System.out.println("imagesArr.length:"+imagesArr.length);
			   	for(int i=0; i<imagesArr.length;i++){
			   		result = images.clickAddImage(imagesArr[i][0]); 
			   		Assert.assertTrue(result, "Failed to Add Image"); 
			   		reporter.log(imagesArr[i][2] + " Image Added Successfully");
			   		   	
		   			result = images.setImageName(imagesArr[i][1]);
		   			Assert.assertTrue(result, "Failed to Set Image Name"); 
		   		   	reporter.log(imagesArr[i][2] + " Image name Entered Successfully");
		   		   	
		   			result = images.selectImageEncoding("JPG");
		   			Assert.assertTrue(result, "Failed to Set Image Encoding"); 
		   		   	reporter.log(imagesArr[i][2] + " Image Encoding selected Successfully");
		   		   	
		   			result =images.selectImageType(imagesArr[i][2]);
		   			Assert.assertTrue(result, "Failed to Set Image Type"); 
		   		   	reporter.log(imagesArr[i][2] + " Image Type selected Successfully");
		   		   	
		   		   	if(xlRead("SVOD").equalsIgnoreCase("Yes")){
		   		   		String [] SVODVarientarr = xlRead("SVODVarient").split(";");
		   		   		for(String SVODVarient :SVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+SVODVarient+" > SVOD",true);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+SVODVarient+" > SVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+SVODVarient+" > SVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	
		   		   	if(xlRead("TVOD").equalsIgnoreCase("Yes")){
		   		   		String [] TVODVarientarr = xlRead("TVODVarient").split(";");
		   		   		for(String TVODVarient :TVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+TVODVarient+" > TVOD",true);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+TVODVarient+" >TSVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+TVODVarient+" > TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		  
		   		   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
		   		   		String [] STVODVarientarr = xlRead("STVODVarient").split(";");
		   		   		for(String STVODVarient :STVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD",true);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+STVODVarient+" >SVOD/TVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	if(xlRead("EST").equalsIgnoreCase("Yes")){
		   		   		String [] ESTVarientarr = xlRead("ESTVarient").split(";");
		   		   		for(String ESTVarient :ESTVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+ESTVarient+" > EST",true);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+ESTVarient+" >EST platform"); 
				   		   	reporter.log(imagesArr[i][3]+ESTVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	
		   			reporter.logWithScreenshot(imagesArr[i][2] + " Image detail Entered Successfully");
		   		   	
		   			
		   			result = images.clickSubmit();
		   			Assert.assertTrue(result, "Failed to Click Update Buton"); 
		   		   	reporter.log(imagesArr[i][2] + " Image update Button clicked Successfully");
		   		   	
		   			Thread.sleep(5000);
		   			
			   		}
			   	
			   	
			    /*------------------------Images Page End ------------------*/	
			   	
			   	
			   	
			   	result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule link Clicked Successfully");
			   	
			 	result =  main.clickAddtoSchedule();
			   	Assert.assertTrue(result, "Failed to Click Add to Schedule Button"); 
			   	reporter.log("Add to Shedule Button Clicked Successfully");
			   	
			   	/*------------------------Schedule Page End ------------------*/	
			   	StagingAddtoSchedule AddtoSchedule = new StagingAddtoSchedule(driver); 
			   	result = AddtoSchedule.clickPlatforms();
			   	Assert.assertTrue(result, "Failed to Select Platforms"); 
			   	reporter.log("Platforms Selected Successfully");		
			   	
			   	String strStartingDate,strEndingDate;
			   	strStartingDate = xlRead("StartDate");
			   	strEndingDate = xlRead("EndDate");
			   	if(getScenerioName().contains("ComingSoon")){
			   		strStartingDate = getFutureDate(1);
			   		strEndingDate = getFutureDate(90);
			   	}else if(strStartingDate.isEmpty() && strEndingDate.isEmpty() ){
			   		strStartingDate = getOldDate(1);
			   		strEndingDate = getFutureDate(90);
			   	}
			   	
			   	result = AddtoSchedule.setStartingDate(strStartingDate);
			   	Assert.assertTrue(result, "Failed to Enter Starting Date"); 
			   	reporter.log("Starting Date Entered Successfully");	
			   	
				result = AddtoSchedule.setEndingDate(strEndingDate);
			   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
			   	reporter.log("Ending Date Entered Successfully");	
			   	
			   	result = AddtoSchedule.selectAssetValue();
			   	Assert.assertTrue(result, "Failed to Select Asset Value"); 
			   	reporter.log("Asset Value Selected Successfully");
			   	
			   	reporter.logWithScreenshot("Data Successfully Entered on Add To Schedule Page");
			   	
			   	result = AddtoSchedule.clickSubmit();
			   	Assert.assertTrue(result, "Failed to click Submit Button"); 
			   	reporter.log("Submit Button Clicked Successfully");	
			   	
			   	/*------------------------Schedule Page End ------------------*/	
			  
			   	
			 	result =  main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to Click Add to Schedule Page"); 
			   	reporter.log("Shedule Page Button Clicked Successfully");
			   	
			   	
			   	/* ------------------ Schdule Entry Page ---------------------- */
			   	
			   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver); 
			   	
			   	result =  scheduleEntry.setPlatformId();
			   	Assert.assertTrue(result, "Platform Entries not Available"); 
			   	reporter.log("Platform ID's Collected Successfully");
			   	
			   	Thread.sleep(60000);
			   	
			   	driver.navigate().refresh();
			   	
			   	Thread.sleep(3000);
			   	
			    AllPlatform =scheduleEntry.getYouviewPlatformId();
			    		    
			   	for(int i = 0 ;i < AllPlatform.size();i++){
			   		
			   		result =  scheduleEntry.ValidateMessage(AllPlatform.get(i));
			   		Assert.assertTrue(result, "Presentation Video quality not Populated"); 
				   	reporter.log("Presentation Video Quality populated succeessfully");
				   	
				   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
				   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
				   	reporter.log("Checkbox clicked succeessfully");
				   	
				   	result = scheduleEntry.clickEditbtn();
				   	Assert.assertTrue(result, "Failed to click Edit Button"); 
				   	reporter.log("Edit Button clicked succeessfully");
				   	
				   	PlatformVarient = scheduleEntry.getPlatformVarient();
				   	PlatformPresentationVideoQuality = scheduleEntry.getPlatformPresentationVideoQuality();
					String strContract = null;
				   	StagingEditSchedule editSchedule  = new StagingEditSchedule(driver); 
				   	if(PlatformVarient.get(AllPlatform.get(i)).equals("SVOD")){
				   		strContract = xlRead("SVODContract");
				   		result = editSchedule.setContract(strContract, AllPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to SVOD Contract for Scheduling ID : "+AllPlatform.get(i)); 
					   	reporter.log("Contract Set succeessfully");
					   	
					    String strSubs = xlRead("Product_Subscription");
				   		result = editSchedule.setSubscriptions(strSubs, AllPlatform.get(i),Flag);
				   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
					   	reporter.log("Contract Set succeessfully");
				   		
				   	}else if(PlatformVarient.get(AllPlatform.get(i)).equals("TVOD")){
				   		strContract = xlRead("TVODContract");
				   		result = editSchedule.setContract(strContract, AllPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
					   	reporter.log("TVOD Contract Set succeessfully");
					   	
					    String strPrice=null;
					   	if(xlRead("TVODVarient").contains(";")){
					   		String [] TVODPricearr = xlRead("TVODPrice").split(";");
					   		if(PlatformPresentationVideoQuality.get(AllPlatform.get(i)).equals("SD")){
					   			strPrice = TVODPricearr[0];
					   		}else{
					   			strPrice = TVODPricearr[1];
					   		}
					   	}else{
					   		strPrice = xlRead("TVODPrice");
					   	}
					   System.out.println("");
				   		result = editSchedule.setPrice(strPrice, AllPlatform.get(i),Flag);
				   		Assert.assertTrue(result, "Failed to Select Price"); 
					   	reporter.log("Price Set succeessfully");
				   		
				   		
				   	}else if(PlatformVarient.get(AllPlatform.get(i)).equals("SVOD/TVOD")){
				   		strContract = xlRead("STVODContract");
				   		result = editSchedule.setContract(strContract, AllPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
					   	reporter.log("TVOD Contract Set succeessfully");
					   	
					    String strSubs = xlRead("Product_Subscription");
				   		result = editSchedule.setSubscriptions(strSubs, AllPlatform.get(i),Flag);
				   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
					   	reporter.log("Contract Set succeessfully");
					   	
					    String strPrice = xlRead("STVODPrice");
				   		result = editSchedule.setPrice(strPrice, AllPlatform.get(i),Flag);
				   		Assert.assertTrue(result, "Failed to Select Price"); 
					   	reporter.log("Price Set succeessfully");
					   	
				   	}else if(PlatformVarient.get(AllPlatform.get(i)).equals("EST")){
				   		strContract = xlRead("ESTContract");
				   		result = editSchedule.setContract(strContract, AllPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to set EST Contract"); 
					   	reporter.log("EST Contract Set succeessfully");
					   	
					   	String strPrice=null;
					   	if(xlRead("ESTVarient").contains(";")){
					   		String [] strPricearr = xlRead("ESTPrice").split(";");
					   		
					   		if(PlatformPresentationVideoQuality.get(AllPlatform.get(i)).equals("SD")){
					   			strPrice = strPricearr[0];
					   		}else{
					   			strPrice = strPricearr[1];
					   		}
					   	}else{
					   		strPrice = xlRead("ESTPrice");
					   	}
					   	
				   		result = editSchedule.setPrice(strPrice, AllPlatform.get(i),Flag);
				   		Assert.assertTrue(result, "Failed to Select Price"); 
					   	reporter.log("Price Set succeessfully");
				   		
				   	}
				   	
				   	String strPlacement = xlRead("Title_Placement");
			   		result = editSchedule.setPlacement(strPlacement, AllPlatform.get(i));
			   		Assert.assertTrue(result, "Failed to Select Placement"); 
				   	reporter.log("Contract Set succeessfully");
				   	
				   	reporter.logWithScreenshot("Edit Shedule Page");
				   	
				//   	result = editSchedule.clickSubmit();
			   		Assert.assertTrue(result, "Failed to click Update Button"); 
				   	reporter.log("Update button clicked succeessfully");
				   	
				   	
			   		
			   	}
			   	
			   	Thread.sleep(3000);
			       
			   	result = scheduleEntry.clickContentLink(strContentName);
		   		Assert.assertTrue(result, "Failed to click Asset title Link"); 
			   	reporter.log("Asset title link clicked succeessfully");
			 	
			   	scheduleEntry = new StagingScheduleEntires(driver); 
			   	
			   	youviewPlatform = scheduleEntry.getYouviewRenditions("Single");
			    vospPlatform =scheduleEntry.getVospRenditions("Single");
			   	
			   	Thread.sleep(3000);
			   	
			    result = main.clickNavLink("Renditions");
			   	Assert.assertTrue(result, "Failed to Click Renditions Link"); 
			   	reporter.log("Renditions Link Clicked Successfully");
			   	
			   	result = main.checkRenditions(Scheduling_Plaforms);
			   	Assert.assertTrue(result, "Renditions not Generated"); 
			   	reporter.log("Renditions generated Successfully");
			   	
			    result = main.validateBothRenditions(vospPlatform, youviewPlatform);
			   	Assert.assertTrue(result, "Renditions Validations got Failed"); 
			   	reporter.log("Renditions Validated Successfully");
			  		  
			    result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule Link Clicked Successfully");
			   	
			    result = main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to Click Schedule Page Button"); 
			   	reporter.log("Schedule Page Button Clicked Successfully");
			   	
			   
		} 
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (result) {
					reporter.log("Status:Passed");

				} else {
					reporter.logWithScreenshot("Script Failed at page");
					reporter.log("Status:Failed");
				}
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@AfterMethod
	public void afterMethod() {
		try {
			//driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
