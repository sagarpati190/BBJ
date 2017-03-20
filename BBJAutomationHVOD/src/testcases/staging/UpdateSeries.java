package testcases.staging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import pages.staging.StagingCreateContent;
import pages.staging.StagingEditSchedule;
import pages.staging.StagingEpisode;
import pages.staging.StagingGroup;
import pages.staging.StagingImages;
import pages.staging.StagingLogin;
import pages.staging.StagingMain;
import pages.staging.StagingScheduleEntires;
import pages.staging.StagingTitle;
import utility.AdvanceReporter;
import utility.Utility;

public class UpdateSeries extends Utility {

	WebDriver driver;
	AdvanceReporter reporter;
	Utility util;
	boolean result;
	String strContentName = null;
	String SchedulingID =null;
	StagingCatalog catalog;
	StagingMain main;
	StagingAddAsset asset;
	StagingTitle title;
	StagingScheduleEntires scheduleEntry;
	private ArrayList<String> allPlatformChk, AllPlatform,vospPlatform,youviewPlatform;
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
	public void UpdateSeriesContent(){
		try {
			
			/*---------------Login------------------*/
			
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
			
			
			/*---------------Login------------------*/
			
			/*---------------Search Content------------------*/
			
			strContentName = xlRead("AssetTitle");

			result = catalog.setSerchText(strContentName);
			Assert.assertTrue(result, "Failed to search the content name");
			reporter.logWithScreenshot("Content Successfully searched");

			result = catalog.clickSearchedContent(strContentName);
			Assert.assertTrue(result, "Searched content could not be clicked");

			/*---------------Search Content------------------*/
			
			//-----------to get the scheduling ID------------
			
			String url = driver.getCurrentUrl();
 			Pattern p = Pattern.compile("(\\d+)");
	  		Matcher m = p.matcher(url);
	  		if (m.find()) {
	  				SchedulingID ="movida_"+m.group()+"_"+xlRead("STVODVarient");
	  				System.out.println("SchedulingID:"+SchedulingID);
	  		}
			
			/*---------------Go to Respective Page for Update------------------*/
			
			if (updateFlg.equalsIgnoreCase("Price")||updateFlg.equalsIgnoreCase("Placement")||updateFlg.equalsIgnoreCase("Subscription")||updateFlg.contains("Expire")) {
			
			
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
				String strScheduling_Plaforms = xlRead("Scheduling_Plaforms");
				
				if(strScheduling_Plaforms.equals("VOSP")){
			   		AllPlatform = scheduleEntry.getVospPlatformId();
			   	}else if(strScheduling_Plaforms.equals("Youview")){
			   		AllPlatform = scheduleEntry.getYouviewPlatformId();
			   	}else{
			   		AllPlatform = scheduleEntry.getAllPlatformId();
			   		
			   	}
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
				   	
				   	
				//   	result = editSchedule.clickSubmit();
					Assert.assertTrue(result, "Failed to click Update Button");
					reporter.log("Update button clicked succeessfully");	
					
				 	if (updateFlg.equalsIgnoreCase("ExpireOneEpisode")){
				   		break;
				   	}
					
			    }
			   	
			  
				
			}
			
			else if (updateFlg.contains("Rating")) {
				
				result = main.clickNavLink("Episodes");
				Assert.assertTrue(result, "Failed to Click Episodes Link");
				reporter.log("Episodes Link Clicked Successfully");
				  
				//int EpiNum = Integer.parseInt(xlRead("EpisodeNum"));
				
				List<WebElement> SeriesTitles = driver.findElements(By.className("title_name"));
				int SeriesNo= SeriesTitles.size();
				System.out.println("value "+SeriesNo);
				
				//StagingMain main = new StagingMain(driver);
				
				for(int j=1; j<= SeriesNo;j++){
					
					result = main.clickNavLink("Episodes");
					Assert.assertTrue(result, "Failed to Click Episodes Link");
					reporter.log("Episodes Link Clicked Successfully");
					
					String strSeriesName=xlRead("AssetTitle");
					String strEpiName =  "EP"+j+"_"+strSeriesName;
					  
					  StagingEpisode episode = new StagingEpisode(driver); 
				   	  result =  episode.clickEpisodeSerieslink(strEpiName);
				      reporter.logWithScreenshot(strEpiName +"link Clicked Successfully");
				      
				      	result = main.clickNavLink("Assets");
						Assert.assertTrue(result, "Failed to Click Assets Link");
						reporter.log("Assets Link Clicked Successfully");
						
						result =  main.editAsset();
						Assert.assertTrue(result, "Failed to Click Metadata Button"); 
						reporter.log("Metadata Button Clicked Successfully");
						
						//Asset Page
						StagingAddAsset asset = new StagingAddAsset(driver);
						
						String strAssetRating =  xlRead("Asset_rating");
					 	result = asset.setAssetRating(strAssetRating);
						Assert.assertTrue(result, "Failed to Select Asset Rating"); 
					   	reporter.log("Asset Rating Selected Successfully");
					   	
					   	result = asset.clickUpdateButton();
						Assert.assertTrue(result, "Failed to Click Update Button"); 
					   	reporter.log("Button Clicked Successfully");
					   	
					   	//Click the Main Series
					   	result = episode.clickEpisodeSerieslink(strSeriesName);
					   	Assert.assertTrue(result, "Failed to Click Series Link"); 
			   		   	reporter.log("Series link clicked Successfully");	
			   		 
			   		 if (updateFlg.equalsIgnoreCase("RatingoneEpisode")){
					   		break;
					   	}
				}	
						result = main.clickNavLink("Schedule");
					   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
					   	reporter.log("Schedule link Clicked Successfully");
				
					   	
					   	result = main.clickSchedulePage();
					   	Assert.assertTrue(result, "Failed to click submit button"); 
					   	reporter.log("Submit button clicked Successfully");	
			}
			
			else if (updateFlg.equalsIgnoreCase("Metadata")) {
				
				result = main.clickNavLink("Episodes");
				Assert.assertTrue(result, "Failed to Click Episodes Link");
				reporter.log("Episodes Link Clicked Successfully");
				  
				//int EpiNum = Integer.parseInt(xlRead("EpisodeNum"));
				
				List <WebElement> SeriesTitles = driver.findElements(By.className("title_name"));
				int SeriesNo= SeriesTitles.size();
				System.out.println("value "+SeriesNo);
				
				StagingMain main = new StagingMain(driver);
				
				for(int j=1; j<= SeriesNo;j++){
					String strSeriesName=xlRead("AssetTitle");
					String strEpiName =  "EP"+j+"_"+strSeriesName;
					
					result = main.clickNavLink("Episodes");
					Assert.assertTrue(result, "Failed to Click Episodes Link");
					reporter.log("Episodes Link Clicked Successfully");
					  
					  StagingEpisode episode = new StagingEpisode(driver); 
				   	  result =  episode.clickEpisodeSerieslink(strEpiName);
				      reporter.logWithScreenshot(strEpiName +"link Clicked Successfully");
				      
				      	result = main.clickNavLink("Title");
						Assert.assertTrue(result, "Failed to Click Assets Link");
						reporter.log("Assets Link Clicked Successfully");
						
						result =  main.clickEditMetadata();
					   	Assert.assertTrue(result, "Failed to Click Metadata Button"); 
					   	reporter.log("Metadata Button Clicked Successfully");
						
						//Title Page
					    StagingTitle title = new StagingTitle(driver);
					    
					      
					  	  
					  	  String strTitleShort = xlRead("Title_ShortDesc");
					   	  result = title.setTitleShortDesc(strTitleShort);
					  	  Assert.assertTrue(result, "Failed to Enter Short Description"); 
					  	  reporter.log("Short Description Entered Successfully");
					  	  
					  	  String strTitleMedium = xlRead("Title_MediumDesc");
					  	  result = title.setTitleMediumDesc(strTitleMedium);
					  	  Assert.assertTrue(result, "Failed to Enter Medium Description"); 
					  	  reporter.log("Medium Description Entered Successfully");
					  	  
					  	  String strTitleLong = xlRead("Title_LongDesc");
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
					  	  
					   	
					   	//Click the Main Series
					   	result = episode.clickEpisodeSerieslink(strSeriesName);
					   	Assert.assertTrue(result, "Failed to Click Series Link"); 
			   		   	reporter.log("Series link clicked Successfully");	
			   		   	
			   		   	
			   		   	//To update the Group Metadata
			   		   	
			   		   	result = main.clickNavLink("Group");
						Assert.assertTrue(result, "Failed to Click Episodes Link");
						reporter.log("Episodes Link Clicked Successfully");
						
						result = main.clickEditMetadata();
						Assert.assertTrue(result, "Failed to Click Metadata "); 
					   	reporter.log("Group Link Clicked Successfully");
					   	
					   	StagingGroup group = new StagingGroup(driver);
					   
					   	String strShortDesc = xlRead("Title_ShortDesc");
				   	   	result = group.setShortDesc(strShortDesc);
				   	   	Assert.assertTrue(result, "Failed to Enter Short Description"); 
				   	   	reporter.log("Short Description Entered Successfully");
				  	  
				   	   	String strMediumDesc = xlRead("Title_MediumDesc");
				   	   	result = group.setMediumDesc(strMediumDesc);
				   	   	Assert.assertTrue(result, "Failed to Enter Medium Description"); 
				   	   	reporter.log("Medium Description Entered Successfully");
				  	  
				   	   	String strLongDesc = xlRead("Title_LongDesc");
				   	   	result = group.setLongDesc(strLongDesc);
				   	   	Assert.assertTrue(result, "Failed to Enter Long Description"); 
				   	   	reporter.log("Long Description Entered Successfully");
				   	   	
				   	   	result = group.clickUpdateButton();
				   	   	Assert.assertTrue(result, "Failed to click update button"); 
				   	   	reporter.log("Update button clicked Successfully");
				   	   	
				   	    
				}
						result = main.clickNavLink("Schedule");
					   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
					   	reporter.log("Schedule link Clicked Successfully");
				
					   	
					   	result = main.clickSchedulePage();
					   	Assert.assertTrue(result, "Failed to click submit button"); 
					   	reporter.log("Submit button clicked Successfully");	
			}
			else if(updateFlg.equalsIgnoreCase("Image")){
				
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
				Assert.assertTrue(result, "Unable to click schedule page");
				reporter.logWithScreenshot("Navigated to schedule entries");
			}
			
			else if(updateFlg.equalsIgnoreCase("Brand")){
				
				result = main.ClickMainEdit();
				Assert.assertTrue(result, "Failed to Click Assets Main Edit Button");
				reporter.log("Assets Main Edit Button Clicked Successfully");
				
				StagingCreateContent CreateContent = new StagingCreateContent(driver);
				
				String strBrandName = xlRead("BrandName");
		   		result = CreateContent.setBrandName(strBrandName);
		   		Assert.assertTrue(result, "Failed to Update Brand"); 
			   	reporter.log("Brand Updated succeessfully");
			   	
			   	result = CreateContent.clickSubmit();
			  	Assert.assertTrue(result, "Failed to click Create Button"); 
			   	reporter.logWithScreenshot("Create Button Clicked Successfully");
			   	
			   	result = main.clickNavLink("Schedule");
				Assert.assertTrue(result, "Failed to Click Schedule Link");
				reporter.log("Schedule link Clicked Successfully");

				result = main.clickSchedulePage();
				Assert.assertTrue(result, "Unable to click schedule page");
				reporter.logWithScreenshot("Navigated to schedule entries");
				
			}
		
	
			/*---------------Go to Respective Page for Update------------------*/
			
			//To Publish the Updated Series Asset-------------------------------
			
			StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver); 
			
			result =  scheduleEntry.setPlatformId();
		   	Assert.assertTrue(result, "Platform Entries not Available"); 
		   	reporter.log("Platform ID's Collected Successfully");
			
			AllPlatform =scheduleEntry.getAllPlatformId();
		   	for(int i=0;i<AllPlatform.size();i++){
		    	Thread.sleep(2000);
		   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
			   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
			   	reporter.log("Checkbox clicked succeessfully");		   	
			}
		    
		    result = scheduleEntry.clickPublishButton();
		    Assert.assertTrue(result, "Failed to click on Publish Button"); 
		   	reporter.log("Publish button clicked succeessfully");
		
		   	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (result) {
				reporter.log("Status:Passed");
				//to write in the Source File
				String Status="Pass"; 
			 	ForSFGCheck(Status, SchedulingID); 

			} else {
				reporter.logWithScreenshot("Script Failed at page");
				reporter.log("Status:Failed");
				String Status="Fail"; 
			 	ForSFGCheck(Status, SchedulingID); 
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
