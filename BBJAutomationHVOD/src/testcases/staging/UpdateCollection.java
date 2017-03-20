package testcases.staging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
import pages.staging.StagingMISReporting;
import pages.staging.StagingMain;
import pages.staging.StagingScheduleEntires;
import pages.staging.StagingTitle;
import pages.staging.StagingVosp;
import utility.AdvanceReporter;
import utility.Utility;

public class UpdateCollection extends Utility {
	  WebDriver driver;
	   AdvanceReporter reporter;
	   private ArrayList<String> AllPlatform,vospPlatform,youviewPlatform;
	   private HashMap<String,String> PlatformVarient,PlatformPresentationVideoQuality =new HashMap<String,String>();    
	   boolean result = false;
	   StagingMain main = null;
	   String SchedulingID =null;
	   
	  @BeforeMethod
	  public void beforeMethod() {
	  
		  ProfilesIni profile = new ProfilesIni();
		  FirefoxProfile myprofile = profile.getProfile("default");
		  
		  driver = new FirefoxDriver(myprofile);
		  driver.manage().window().maximize();
		  reporter = new AdvanceReporter(driver);
	  }

	  @AfterMethod
	  public void afterMethod() {
		  
		//  driver.quit();
	  }
	  
	  @Test
	  public void UpdateCollection() {
		 
		   
		  try {
			  
			   /* --------------------------Login Start ------------------------------------- */
			  
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
			  	  		  	  
			  	/* --------------------------Login End ------------------------------------- */
			  	  
			  	  String UpdateFlag =xlRead("UpdateFlag");
			  	  
			  	  switch(UpdateFlag){
			  	  
			  	  case "Add_Episode":
			  		  	result = this.addEpisode();
			  		    break;
			  		    
			  	  case "Metadata":
			  		 	result =  this.UpdateMedata();
			  		    break;
			  		    
			  	  case "Rating":
			  		 	result =  this.UpdateRating();
			  		    break;
			  		    
			  	  case "Image":
			  		  result =  this.UpdateImage();
			  		  break;
		  		    
			  	  case "Placement":
			  		 	result =  this.UpdatePlacement();
			  		    break;
		  		    
			  	  case "Subscription":
				  		 result = this.UpdateSubscription();
				  		 break;
			  		  
			  	 case "Price":
			  		  result = this.UpdatePrice();
			  		  break;
			  		  
			  	 case "ExpireAll":
			  		  result = this.Expire("All");
			  		  break;
			  		  
			  	 case "ExpireOneEpisode":
			  		  result = this.Expire("OneEpisode");
			  		  break;
			  		  
			  		    
			  		    
			  	  default:
			  		  System.out.println("Please Select Correct Update Field");
			  	  
			  	  }
			  	  			   	
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}finally{
					try {
						  if(result){
						 	reporter.log("Status:Passed");
						 	//To write in the Source File
						 	String Status="Pass"; 
						 	ForSFGCheck(Status, SchedulingID); 
						   
						  }else{
							 reporter.logWithScreenshot("Script Failed at page");
							 reporter.log("Status:Failed");
							//To write in the Source File
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

	  
	  private boolean Expire(String strExpireType) {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					result = main.clickNavLink("Schedule");
					Assert.assertTrue(result, "Failed to Click Schedule Link");
					reporter.log("Schedule Link Clicked Successfully");
					
					result =  main.clickSchedulePage();
				   	Assert.assertTrue(result, "Failed to Click Shedule Page Button"); 
				   	reporter.log("Shedule Page Button Clicked Successfully");
					
				   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver);
				   	
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
				   		AllPlatform = scheduleEntry.getVospPlatformId();
				   		
				   	}
					
				   	
				   	StagingEditSchedule editSchedule = new StagingEditSchedule(driver);
					String strEndingDate = getFutureTime(2);
					
				    if(strExpireType.equals("OneEpisode")){
				    	result = scheduleEntry.clickCheckBox(AllPlatform.get(0));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");	
					   	
					   	result = scheduleEntry.clickEditbtn();
						Assert.assertTrue(result, "Failed to click Edit Button"); 
						reporter.log("Edit Button clicked succeessfully");
						
						result = editSchedule.setExpiryDate(strEndingDate,AllPlatform.get(0));
					   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
					   	reporter.log("Ending Date Entered Successfully");
					   	
					 // 	result = editSchedule.clickSubmit();
						Assert.assertTrue(result, "Failed to click Update Button");
						reporter.log("Update button clicked succeessfully");
						
						
						
				    }else{
				    	for(int i=0;i<AllPlatform.size();i++){
					    	Thread.sleep(2000);
					   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
						   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
						   	reporter.log("Checkbox clicked succeessfully");
						   	
						   	result = scheduleEntry.clickEditbtn();
							Assert.assertTrue(result, "Failed to click Edit Button"); 
							reporter.log("Edit Button clicked succeessfully");
							
							result = editSchedule.setExpiryDate(strEndingDate,AllPlatform.get(i));
						   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
						   	reporter.log("Ending Date Entered Successfully");
						   	
				//		   	result = editSchedule.clickSubmit();
							Assert.assertTrue(result, "Failed to click Update Button");
							reporter.log("Update button clicked succeessfully");
						}  
				    }
						
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					}
				    
				    result = scheduleEntry.clickPublishButton();
				    Assert.assertTrue(result, "Failed to click on Publish Button"); 
				   	reporter.log("Publish button clicked succeessfully");
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdatePrice() {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					
					result = main.clickNavLink("Schedule");
					Assert.assertTrue(result, "Failed to Click Schedule Link");
					reporter.log("Schedule Link Clicked Successfully");
					
					result =  main.clickSchedulePage();
				   	Assert.assertTrue(result, "Failed to Click Shedule Page Button"); 
				   	reporter.log("Shedule Page Button Clicked Successfully");
					
				   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver);
				   	
				   	result = scheduleEntry.setPlatformId();
					Assert.assertTrue(result, "Platform Entries not Available");
					reporter.log("Platform ID's Collected Successfully");

					Thread.sleep(5000);
				   	
					AllPlatform = scheduleEntry.getAllPlatformId();
					
					StagingEditSchedule editSchedule = new StagingEditSchedule(driver);
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	
					   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					   	
					   	result = scheduleEntry.clickEditbtn();
					   	Assert.assertTrue(result, "Failed to click Edit Button"); 
					   	reporter.log("Edit Button clicked succeessfully");
					   	
					   				   		//Checking placemnt

				   		
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
						}else if (PlatformVarient.get(AllPlatform.get(i)).equals("SVOD/TVOD")) {
								if (xlRead("STVODVarient").contains(";")) {
									String[] strPricearr = xlRead("STVODPrice").split(";");
									if (PlatformPresentationVideoQuality.get(AllPlatform.get(i)).equals("SD")) {
										strPrice = strPricearr[0];
									} else {
										strPrice = strPricearr[1];
									}
								} else {
									strPrice = xlRead("STVODPrice");
								}
							}
						System.out.println("strPrice------>"+strPrice);
						result = editSchedule.setPrice(strPrice,AllPlatform.get(i), "Update");
						Assert.assertTrue(result, "Failed to Select Price");
						reporter.log("Price Set succeessfully");
					
				   	
					   					   	
					   	
					  // 	result = editSchedule.clickSubmit();
						Assert.assertTrue(result, "Failed to click Update Button");
						reporter.log("Update button clicked succeessfully");					
						
				    }
					
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					}
				    
				    result = scheduleEntry.clickPublishButton();
				    Assert.assertTrue(result, "Failed to click on Publish Button"); 
				   	reporter.log("Publish button clicked succeessfully");
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdateSubscription() {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					
					result = main.clickNavLink("Schedule");
					Assert.assertTrue(result, "Failed to Click Schedule Link");
					reporter.log("Schedule Link Clicked Successfully");
					
					result =  main.clickSchedulePage();
				   	Assert.assertTrue(result, "Failed to Click Shedule Page Button"); 
				   	reporter.log("Shedule Page Button Clicked Successfully");
					
				   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver);
				   	
				   	result = scheduleEntry.setPlatformId();
					Assert.assertTrue(result, "Platform Entries not Available");
					reporter.log("Platform ID's Collected Successfully");

					Thread.sleep(5000);
				   	
					AllPlatform = scheduleEntry.getAllPlatformId();
					
					StagingEditSchedule editSchedule = new StagingEditSchedule(driver);
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	
					   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					   	
					   	result = scheduleEntry.clickEditbtn();
					   	Assert.assertTrue(result, "Failed to click Edit Button"); 
					   	reporter.log("Edit Button clicked succeessfully");
					   	
					   				   		//Checking placemnt
					   	String strSubs = xlRead("Product_Subscription");
						result = editSchedule.setSubscriptions(strSubs,AllPlatform.get(i), "Update");
						Assert.assertTrue(result,"Unable to select the subscriptions");
						reporter.logWithScreenshot("SVOD subscription changed successfully");
					   					   	
					   	
				//	   	result = editSchedule.clickSubmit();
						Assert.assertTrue(result, "Failed to click Update Button");
						reporter.log("Update button clicked succeessfully");					
						
				    }
					
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					}
				    
				    result = scheduleEntry.clickPublishButton();
				    Assert.assertTrue(result, "Failed to click on Publish Button"); 
				   	reporter.log("Publish button clicked succeessfully");
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdatePlacement() {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					
					result = main.clickNavLink("Schedule");
					Assert.assertTrue(result, "Failed to Click Schedule Link");
					reporter.log("Schedule Link Clicked Successfully");
					
					result =  main.clickSchedulePage();
				   	Assert.assertTrue(result, "Failed to Click Shedule Page Button"); 
				   	reporter.log("Shedule Page Button Clicked Successfully");
					
				   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver);
				   	
				   	result = scheduleEntry.setPlatformId();
					Assert.assertTrue(result, "Platform Entries not Available");
					reporter.log("Platform ID's Collected Successfully");

					Thread.sleep(5000);
				   	
					AllPlatform = scheduleEntry.getAllPlatformId();
					
					StagingEditSchedule editSchedule = new StagingEditSchedule(driver);
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	
					   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					   	
					   	result = scheduleEntry.clickEditbtn();
					   	Assert.assertTrue(result, "Failed to click Edit Button"); 
					   	reporter.log("Edit Button clicked succeessfully");
					   	
					   				   		//Checking placemnt
					   	String strPlacement = xlRead("Title_Placement");
					   	result = editSchedule.setPlacement(strPlacement, AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to Select Placement"); 
						reporter.log("Contract Set succeessfully");
					   					   	
					   	
				//	   	result = editSchedule.clickSubmit();
						Assert.assertTrue(result, "Failed to click Update Button");
						reporter.log("Update button clicked succeessfully");					
						
				    }
					
					for(int i=0;i<AllPlatform.size();i++){
				    	Thread.sleep(2000);
				   	   	result = scheduleEntry.clickCheckBox(AllPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");		   	
					}
				    
				    result = scheduleEntry.clickPublishButton();
				    Assert.assertTrue(result, "Failed to click on Publish Button"); 
				   	reporter.log("Publish button clicked succeessfully");
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdateImage() {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					result = main.clickNavLink("Title");
					Assert.assertTrue(result, "Failed to click title link");
					reporter.log("Title Link Clicked Successfully");
					
					int EpiNum = main.getEpisodenum();
								
					
					
					for(int j=1; j <= EpiNum ; j++ ){
						
						result = main.clickNavLink("Title");
						Assert.assertTrue(result, "Failed to click title link");
						reporter.log("Title Link Clicked Successfully");
						 
						result = main.clickEpiName(j);
						Assert.assertTrue(result, "Failed to click Episode Name link");
						reporter.log("Episode Name Link Clicked Successfully");
						 
						result = main.clickNavLink("Images");
						Assert.assertTrue(result, "Failed to click Assets link");
						reporter.log("Assets Link Clicked Successfully");
						
						  /*------------------------Images Page Start ------------------*/	
					   	String Scheduling_Plaforms = null;
					   	String strVOSP_Version = xlRead("VOSP_Version");
					   	String[][] imagesArr = null;
					   	StagingImages images = new StagingImages(driver);
						if(strVOSP_Version.equalsIgnoreCase("2.1")){
					   		
					   		
					   		Scheduling_Plaforms = xlRead("Scheduling_Plaforms");
					   		
					   		if(Scheduling_Plaforms.equalsIgnoreCase("both")){
					   			imagesArr  = new String [2][4];
					   			imagesArr[0][0]= "SinglePackshotupdate.jpg";
					   			imagesArr[0][1]= "SinglePackshotupdate.jpg";
					   			imagesArr[0][2]= "Packshot";
					   			//imagesArr[0][3]= "VOSP2 > ";
					   			
					   			imagesArr[1][0]= "Hiresupdate.jpg";
					   			imagesArr[1][1]= "Hiresupdate.jpg";
					   			imagesArr[1][2]= "HiRes Packshot";	
					   			//imagesArr[1][3]= "YouView > ";
					   			
					   		}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
					   			imagesArr = new String [1][4];
					   			imagesArr[0][0]= "SinglePackshotupdate.jpg";
					   			imagesArr[0][1]= "SinglePackshotupdate.jpg";
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
					
					   	
					  	//Click the Main Series
					   	StagingEpisode episode = new StagingEpisode(driver);
					   	result = episode.clickEpisodeSerieslink(strContentName);
					   	Assert.assertTrue(result, "Failed to Click Series Link"); 
			   		   	reporter.log("Series link clicked Successfully");	
					} 
					
					
					result = main.clickNavLink("Images");
					Assert.assertTrue(result, "Failed to click Assets link");
					reporter.log("Assets Link Clicked Successfully");
					
					  /*------------------------Images Page Start ------------------*/	
				   	String Scheduling_Plaforms = null;
				   	String strVOSP_Version = xlRead("VOSP_Version");
				   	String[][] imagesArr = null;
				   	StagingImages images = new StagingImages(driver);
					if(strVOSP_Version.equalsIgnoreCase("2.1")){
				   		
				   		
				   		Scheduling_Plaforms = xlRead("Scheduling_Plaforms");
				   		
				   		if(Scheduling_Plaforms.equalsIgnoreCase("both")){
				   			imagesArr  = new String [2][4];
				   			imagesArr[0][0]= "SinglePackshotupdate.jpg";
				   			imagesArr[0][1]= "packshotImageUpdate.jpg";
				   			imagesArr[0][2]= "Packshot";
				   			//imagesArr[0][3]= "VOSP2 > ";
				   			
				   			imagesArr[1][0]= "Hiresupdate.jpg";
				   			imagesArr[1][1]= "Hiresupdate.jpg";
				   			imagesArr[1][2]= "HiRes Packshot";	
				   			//imagesArr[1][3]= "YouView > ";
				   			
				   		}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
				   			imagesArr = new String [1][4];
				   			imagesArr[0][0]= "SinglePackshotupdate.jpg";
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
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdateRating() {

			// TODO Auto-generated method stub
			   try{
				   	StagingCatalog catalog = new StagingCatalog(driver);  

				   	String strContentName = xlRead("AssetTitle");
					result = catalog.setSerchText(strContentName);
					Assert.assertTrue(result, "Failed to search the content name");
					reporter.logWithScreenshot("Content Successfully searched");

					result = catalog.clickSearchedContent(strContentName);
					Assert.assertTrue(result, "Searched content could not be clicked");
				  	reporter.log("Searched Content clicked successfully");
				  	
					StagingMain main = new StagingMain(driver);  
					
					result = main.clickNavLink("Title");
					Assert.assertTrue(result, "Failed to click title link");
					reporter.log("Title Link Clicked Successfully");
					
					int EpiNum = main.getEpisodenum();
								
					
					
					for(int j=1; j <= 1 ; j++ ){
						
						result = main.clickNavLink("Title");
						Assert.assertTrue(result, "Failed to click title link");
						reporter.log("Title Link Clicked Successfully");
						 
						result = main.clickEpiName(j);
						Assert.assertTrue(result, "Failed to click Episode Name link");
						reporter.log("Episode Name Link Clicked Successfully");
						 
						result = main.clickNavLink("Assets");
						Assert.assertTrue(result, "Failed to click Assets link");
						reporter.log("Assets Link Clicked Successfully");
						
						result = main.editAsset();
						Assert.assertTrue(result, "Failed to click Edit Asset Button");
						reporter.log("Edit Metadata Button Clicked Successfully");
															
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
					  	StagingEpisode episode = new StagingEpisode(driver);
					   	result = episode.clickEpisodeSerieslink(strContentName);
					   	Assert.assertTrue(result, "Failed to Click Series Link"); 
			   		   	reporter.log("Series link clicked Successfully");	
					} 
					
					   	   	
			   	   	result = main.clickNavLink("Schedule");
				   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
				   	reporter.log("Schedule link Clicked Successfully");
			
				   	
				   	result = main.clickSchedulePage();
				   	Assert.assertTrue(result, "Failed to click submit button"); 
				   	reporter.log("Submit button clicked Successfully");	
				   	
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
				   	
				    return true;
			   }catch(Exception e){
				   return false;
			   }
	}

	private boolean UpdateMedata() {
		// TODO Auto-generated method stub
		   try{
			   	StagingCatalog catalog = new StagingCatalog(driver);  

			   	String strContentName = xlRead("AssetTitle");
				result = catalog.setSerchText(strContentName);
				Assert.assertTrue(result, "Failed to search the content name");
				reporter.logWithScreenshot("Content Successfully searched");

				result = catalog.clickSearchedContent(strContentName);
				Assert.assertTrue(result, "Searched content could not be clicked");
			  	reporter.log("Searched Content clicked successfully");
			  	
				StagingMain main = new StagingMain(driver);  
				
				result = main.clickNavLink("Title");
				Assert.assertTrue(result, "Failed to click title link");
				reporter.log("Title Link Clicked Successfully");
				
				int EpiNum = main.getEpisodenum();
							
				
				
				for(int j=1; j <= EpiNum ; j++ ){
					
					result = main.clickNavLink("Title");
					Assert.assertTrue(result, "Failed to click title link");
					reporter.log("Title Link Clicked Successfully");
					 
					result = main.clickEpiName(j);
					Assert.assertTrue(result, "Failed to click Episode Name link");
					reporter.log("Episode Name Link Clicked Successfully");
					 
					result = main.clickNavLink("Title");
					Assert.assertTrue(result, "Failed to click title link");
					reporter.log("Title Link Clicked Successfully");
					
					result = main.clickEditMetadata();
					Assert.assertTrue(result, "Failed to click Edit Metadata Button");
					reporter.log("Edit Metadata Button Clicked Successfully");
														
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
				  	StagingEpisode episode = new StagingEpisode(driver);
				   	result = episode.clickEpisodeSerieslink(strContentName);
				   	Assert.assertTrue(result, "Failed to Click Series Link"); 
		   		   	reporter.log("Series link clicked Successfully");	
				} 
				
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
		   	   	
		   	   	result = main.clickNavLink("Schedule");
			   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
			   	reporter.log("Schedule link Clicked Successfully");
		
			   	
			   	result = main.clickSchedulePage();
			   	Assert.assertTrue(result, "Failed to click submit button"); 
			   	reporter.log("Submit button clicked Successfully");	
			   	
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
			   	
			    return true;
		   }catch(Exception e){
			   return false;
		   }
		  			  
		
	}

	public boolean addEpisode() {
		// TODO Auto-generated method stub
		  
		  System.out.println("Inside Add Episode");
		try{
			  int EpiNum = Integer.parseInt(xlRead("EpisodeNum"));
		  	  for(int j=1; j <= EpiNum ; j++ ){
		  		  
			  	  StagingCatalog  catalog = new StagingCatalog(driver);
			  	  result = catalog.navigatetoCatalog();
			  	  Assert.assertTrue(result, "Navigate to catalog failed"); 
			  	  reporter.log("navigate to catalog Successfully");
		  	  	  	  
			  	  result = catalog.clickCreateImport();
			  	  Assert.assertTrue(result, "Failed to click createImport button"); 
			  	  reporter.log("create Import Button Clicked Successfully");
		  	  
			  	  String contentType = xlRead("Title_Type");
			  	  if(contentType.contains("Trailer") || contentType.contains("Short-form")){
			  		contentType = "Feature";
			  	  }else{
			  		contentType = xlRead("Title_Type");
			  	  }
			  	  
			  	  System.out.println("contentType:"+contentType);
		  	  
			  	  result = catalog.selectContentType(contentType);
			  	  Assert.assertTrue(result, "Failed to Select Create Feature"); 
			   	  reporter.logWithScreenshot("Create Feature Button Clicked Successfully");
		    
		  	/* --------------------------Catalog End ------------------------------------- */  
		   	  
		    /* --------------------------Create Page Start ------------------------------------- */   
			   	  StagingCreateContent createContent = new StagingCreateContent(driver);	
			   	  
			   	  String EpisodeName  = "Episode" +j+"Name";
			   	  
			   	  String strTitleName=xlRead(EpisodeName);
			   	  
			   	  result = createContent.setTitleName(strTitleName);
			   	  Assert.assertTrue(result, "Failed to Enter Title Name"); 
			   	  reporter.log("Title Name Entered Successfully");
		   	  
			   	  String strLicensor=xlRead("Licensor");
			   	  result = createContent.setLicensorTitle(strLicensor);
			   	  Assert.assertTrue(result, "Failed to Enter Licensor Name"); 
			   	  reporter.log("Licensor Name Entered Successfully");
		   	  
		       	  result = createContent.clickSubmit();
			  	  Assert.assertTrue(result, "Failed to click Create Button"); 
			   	  reporter.logWithScreenshot("Create Button Clicked Successfully");
		   /* --------------------------Create Page End ------------------------------------- */   	  
			   	  
			   	  
			   	  String strVOSP_Version = xlRead("VOSP_Version");
			   	  System.out.println("VOSP_Version:"+strVOSP_Version);
			   	  StagingMain main = new StagingMain(driver);
			   	  if(strVOSP_Version.equalsIgnoreCase("2.1")){                            //For VOSP Version 2.1
			   		  result = main.clickNavLink("VOSP");
				   	  Assert.assertTrue(result, "Failed to Click VOSP Link"); 
				   	  reporter.log("VOSP Link Clicked Successfully");
			   	    	 
				   	  result =  main.clickEditMetadata();
				   	  Assert.assertTrue(result, "Failed to Click Metadata Button"); 
				   	  reporter.log("Metadata Button Clicked Successfully");
			   	  
			   	  /*------------------------Vosp Page Start ------------------*/
				   	  StagingVosp vosp = new StagingVosp(driver);
				   	  result =  vosp.clickrdoVOSP();
				   	  Assert.assertTrue(result, "Failed to click VOSP Radio Button "); 
				   	  reporter.log("VOSP Radio Button Clicked Successfully");
				   	  
				   	  result =  vosp.clickUpdateButton();
				   	  Assert.assertTrue(result, "Failed to click Update Button "); 
				   	  reporter.logWithScreenshot("Update Button Clicked Successfully");
				   	 /*------------------------Vosp Page End ------------------*/
			   	  }
		   	  
		   	   	result = main.clickNavLink("Title");
		   	   	Assert.assertTrue(result, "Failed to Click Title Link"); 
		   	   	reporter.log("Title	 Link Clicked Successfully");
		   	 
		   	   	result =  main.clickEditMetadata();
		   	   	Assert.assertTrue(result, "Failed to Click Metadata Button"); 
		   	   	reporter.log("Metadata Button Clicked Successfully");
		   	
		   	   	/*------------------------Title Page Start ------------------*/
		   	   	StagingTitle title = new StagingTitle(driver);
		   	  
		   	   	String strTitleType = xlRead("Title_Type");
		   	   	System.out.println("Title Type:"+strTitleType);
		   	   	result = title.setTitleType(strTitleType);
		   	   	Assert.assertTrue(result, "Failed to Select Title Type"); 
		   	   	reporter.log("Title Type Selected Successfully");
		  	  
		   	   	System.out.println("Title Name:"+strTitleName);
		   	   	result = title.setTitleName(strTitleName);
		   	   	Assert.assertTrue(result, "Failed to Enter Title Name"); 
		   	   	reporter.log("Title Name Entered Successfully");
		  	  
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
		  	  
			  
		     
			    String strTitleCategory = xlRead("Title_Category");
		   	  	System.out.println("Title_Category:"+strTitleCategory);
		   	  	result = title.setTitleCategory(strTitleCategory);
		   	  	Assert.assertTrue(result, "Failed to Select Title Category"); 
		   	  	reporter.log("Title Category Selected Successfully");
		  	  
		  	  
			  	String strTitleYear = xlRead("Title_ReleaseYr");
			   	System.out.println("Title Year:"+strTitleYear);
			  	result = title.setTitleYear(strTitleYear);
			  	Assert.assertTrue(result, "Failed to Enter Title Year"); 
			  	reporter.log("Title Year Entered Successfully");
		  	   	
			  	String strProductSubscription = xlRead("Product_Subscription");
			  	 if(strProductSubscription.equalsIgnoreCase("BT Vision Music") || strProductSubscription.equalsIgnoreCase("BT Vision Kids") ){ 
				  	  result = title.setTitleArtistName("Automation");
				  	  Assert.assertTrue(result, "Failed to Enter Artist Name"); 
				  	  reporter.log("Artist Name Entered Successfully");
			  	 }
		  	 
			  	 String EST = xlRead("EST");   // IF Asset is EST HD then we have to enter Linked Asset ID Value which is 7 digit and present in URL
			  	 if(EST.equalsIgnoreCase("Yes")){
			  		 String ESTVarient = xlRead("ESTVarient");
			  		 if(ESTVarient.contains("HD")){
			  			 String url = driver.getCurrentUrl();
			  			 String LinkedAssetID = null;
				  			Pattern p = Pattern.compile("(\\d+)");
				  			Matcher m = p.matcher(url);
				  			if (m.find()) {
				  				LinkedAssetID =m.group();
				  			}
				  			
				  			result = title.setTitleLinkedID(LinkedAssetID);
						  	Assert.assertTrue(result, "Failed to Set Linked Asset ID"); 
						  	reporter.log("Linked Asset ID set Successfully");
			  		 }
			  		 
			  	 } 
			  	 reporter.logWithScreenshot("Title Metadata Entered Successfully");
		  	 
			  	 result = title.clickSubmit();
			  	 Assert.assertTrue(result, "Failed to click on Submit Button"); 
			  	 reporter.log("Submit Button Clicked Successfully");
		  	  
			 /*------------------------Title Page End ------------------*/
		   	
			   	result = main.clickNavLink("Asset");
			   	Assert.assertTrue(result, "Failed to Click Asset Link"); 
			   	reporter.log("Title	 Link Clicked Successfully");
			   	 
			   	result =  main.clickAddAsset();
			   	Assert.assertTrue(result, "Failed to Click Add Asset Button"); 
			   	reporter.log("Add Asset Button Clicked Successfully");
		  	 
		    /*------------------------Asset Page End ------------------*/
		   	
			   	StagingAddAsset asset = new StagingAddAsset(driver);
			   	
			   	String strAssetDesc =  xlRead("Asset_Description");
			   	result = asset.setAssetDesc(strAssetDesc);  
				Assert.assertTrue(result, "Failed to Enter Asset Desc"); 
			   	reporter.log("Asset Description Entered Successfully");
			   	
				String strAssetRuntime =  "02:00:00";
			   	result = asset.setAssetRuntime(strAssetRuntime);
				Assert.assertTrue(result, "Failed to Enter Asset Runtime"); 
			   	reporter.log("Asset Runtime Entered Successfully");
			   	
			   	String strAssetLanguage =  "English";
			   	result = asset.setAssetLanguage(strAssetLanguage);
				Assert.assertTrue(result, "Failed to Select Asset language"); 
			   	reporter.log("Asset Language Selected Successfully");
			   	
			 	String strAssetRating =  xlRead("Asset_rating");
			 	result = asset.setAssetRating(strAssetRating);
				Assert.assertTrue(result, "Failed to Select Asset Rating"); 
			   	reporter.log("Asset Rating Selected Successfully");
			    if(EST.equalsIgnoreCase("Yes")){
			  		String ESTVarient = xlRead("ESTVarient");
			  		if(ESTVarient.contains("HD")){
			  			String strAssetResolution = "HD";		  			
			  			result = asset.setAssetResolution(strAssetResolution);
			  			Assert.assertTrue(result, "Failed to Select Asset Resolution"); 
			  		   	reporter.log("Asset Resolution Selected Successfully");
			  		 }
			  		 
			  	 }
			    
			   	
			 	result = asset.setAssetRatio();
				Assert.assertTrue(result, "Failed to Select Asset Ratio"); 
			   	reporter.log("Asset Ratio Selected Successfully");
			   
			    reporter.logWithScreenshot("Asset Metadata Entered Successfully");
			    
	
			 	result = asset.clickUpdateButton();
				Assert.assertTrue(result, "Failed to Click Update Button"); 
			   	reporter.log("Button Clicked Successfully");
			    /*------------------------Asset Page End ------------------*/
		   	
				result = main.clickNavLink("Images");
			   	Assert.assertTrue(result, "Failed to Click Image Link"); 
			   	reporter.log("Title	Image Clicked Successfully");
			   	StagingImages images = new StagingImages(driver);
		   	
		   
			    /*------------------------Images Page Start ------------------*/	
			   	String Scheduling_Plaforms = null;
			   	if(strVOSP_Version.equalsIgnoreCase("2.1")){
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
			   		   			result =images.setImagePlatform(imagesArr[i][3]+SVODVarient+" > SVOD",false);
				   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+SVODVarient+" > SVOD platform"); 
					   		   	reporter.log(imagesArr[i][3]+SVODVarient+" > SVOD platform Set Successfully");
			   		   		}
			   		   	}
			   		   	
			   		   	if(xlRead("TVOD").equalsIgnoreCase("Yes")){
			   		   		String [] TVODVarientarr = xlRead("TVODVarient").split(";");
			   		   		for(String TVODVarient :TVODVarientarr){
			   		   			result =images.setImagePlatform(imagesArr[i][3]+TVODVarient+" > TVOD",false);
				   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+TVODVarient+" >TSVOD platform"); 
					   		   	reporter.log(imagesArr[i][3]+TVODVarient+" > TVOD platform Set Successfully");
			   		   		}
			   		   	}
			   		  
			   		   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
			   		   		String [] STVODVarientarr = xlRead("STVODVarient").split(";");
			   		   		for(String STVODVarient :STVODVarientarr){
			   		   			result =images.setImagePlatform(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD",false);
				   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+STVODVarient+" >SVOD/TVOD platform"); 
					   		   	reporter.log(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
			   		   		}
			   		   	}
			   		   	if(xlRead("EST").equalsIgnoreCase("Yes")){
			   		   		String [] ESTVarientarr = xlRead("ESTVarient").split(";");
			   		   		for(String ESTVarient :ESTVarientarr){
			   		   			result =images.setImagePlatform(imagesArr[i][3]+ESTVarient+" > EST",false);
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
			   	}
			   	
			    /*------------------------Images Page End ------------------*/
			   	
				/* MIS REporting Page  */
			   	if(strProductSubscription.equalsIgnoreCase("BT Vision Music") || strProductSubscription.equalsIgnoreCase("BT Vision Kids") ){ 
			    	
			    	result = main.clickNavLink("MIS Reporting");
				   	Assert.assertTrue(result, "Failed to Click MIS Reporting Link"); 
				   	reporter.log("MIS Reporting link Clicked Successfully");
				   	
				    result =  main.clickEditMetadata();
				   	Assert.assertTrue(result, "Failed to Click Metadata Button"); 
				   	reporter.log("Metadata Button Clicked Successfully");
				   	
				   	StagingMISReporting MISReporting = new StagingMISReporting(driver);
				   	
				   	result = MISReporting.setRoyaltyCategory("Music-Shortform");
				   	Assert.assertTrue(result, "Failed to set Royalty Category"); 
				   	reporter.log("Royalty Category Set Successfully");
				   	
					result = MISReporting.setISRCValue("USUV71100181");
				   	Assert.assertTrue(result, "Failed to set ISRC Value"); 
				   	reporter.log("ISRC Value Set Successfully");
				   	
				   	
				   	result = MISReporting.clickUpdateButton();
				   	Assert.assertTrue(result, "Failed to click Update button"); 
				   	reporter.log("Update Button Clicked Successfully");
				  	    	
			  	 }
			    
			    /* MIS REporting Page  */

		  	  }
		  	  
		  	StagingCatalog catalog = new StagingCatalog(driver);  
		  	
		  	String strContentName = xlRead("AssetTitle");

			result = catalog.setSerchText(strContentName);
			Assert.assertTrue(result, "Failed to search the content name");
			reporter.logWithScreenshot("Content Successfully searched");

			result = catalog.clickSearchedContent(strContentName);
			Assert.assertTrue(result, "Searched content could not be clicked");
		  	reporter.log("Searched Content clicked successfully");
		  	
			StagingMain main = new StagingMain(driver);  
			
			result = main.clickNavLink("Title");
			Assert.assertTrue(result, "Failed to click title link");
			reporter.log("Title Link Clicked Successfully");
			
			result = main.clickEditTitles();
			Assert.assertTrue(result, "Failed to click Edit Title Button");
			reporter.log("Edit Title button Clicked Successfully");
			
			StagingCreateContent create = new StagingCreateContent(driver);
			
			for(int j=1; j <= EpiNum ; j++ ){
				 String EpisodeName  = "Episode" +j+"Name";
				 String strEpisodeName=xlRead(EpisodeName);
				 
				 result = create.addEpisodeCollection(strEpisodeName);
				 Assert.assertTrue(result, "Failed to Add Episode");
				 reporter.log("Episode Added Successfully");
			}
			
			result = create.clickUpdateTitle();
			Assert.assertTrue(result, "Failed to click submit button");
			reporter.log("Submit Button Clicked Successfully");
			
			int numOldEpisode = main.getEpisodenum() - EpiNum;
			System.out.println("numOldEpisode"+numOldEpisode);
			
			result = main.clickNavLink("Schedule");
			Assert.assertTrue(result, "Failed to click Schedule link");
			reporter.log("Schedule Link Clicked Successfully");
			
			result = main.clickAddtoSchedule();
			Assert.assertTrue(result, "Failed to click Add to Schedule button");
			reporter.log("Add to Schedule button Clicked Successfully");
			
			StagingAddtoSchedule addtoSchedule = new StagingAddtoSchedule(driver);
			
			
			result = addtoSchedule.selectScheduleType("only...");
			Assert.assertTrue(result, "Failed to select Schedule type");
			reporter.log("Schedule type selected successfully");
			String addEpisodes = "";
			
			for(int i=numOldEpisode+1;i<=(EpiNum+numOldEpisode);i++){
				addEpisodes = addEpisodes + "," + i  ;
			}
			System.out.println("addEpisodes:"+addEpisodes);
			
			
			result = addtoSchedule.setEpisodes(addEpisodes);
			Assert.assertTrue(result, "Failed to select Schedule type");
			reporter.log("Schedule type selected successfully");
			
			String Scheduling_Plaforms = null;
		   	Scheduling_Plaforms = xlRead("Scheduling_Plaforms");
		   		
		   		String[][] platFormArr = null;
				if(Scheduling_Plaforms.equalsIgnoreCase("both")){
					platFormArr = new String [2][1];
					platFormArr[0][0]= "VOSP2 > ";
					platFormArr[1][0]= "YouView > ";
		   			
		   		}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
		   			platFormArr = new String [1][1];
		   			platFormArr[0][0]= "VOSP2 > ";
		   		}
		   		
		   		System.out.println("platFormArr.length:"+platFormArr.length);
		   		for(int i=0; i<platFormArr.length;i++){
		   			if(xlRead("SVOD").equalsIgnoreCase("Yes")){
		   		   		String [] SVODVarientarr = xlRead("SVODVarient").split(";");
		   		   		for(String SVODVarient :SVODVarientarr){
		   		   			result =addtoSchedule.setPlatform(platFormArr[i][0]+SVODVarient+" > SVOD");
			   		   		Assert.assertTrue(result, "Failed to set "+ platFormArr[i][0]+SVODVarient+" > SVOD platform"); 
				   		   	reporter.log(platFormArr[i][0]+SVODVarient+" > SVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	
		   		   	if(xlRead("TVOD").equalsIgnoreCase("Yes")){
		   		   		String [] TVODVarientarr = xlRead("TVODVarient").split(";");
		   		   		for(String TVODVarient :TVODVarientarr){
		   		   			result =addtoSchedule.setPlatform(platFormArr[i][0]+TVODVarient+" > TVOD");
			   		   		Assert.assertTrue(result, "Failed to set "+ platFormArr[i][0]+TVODVarient+" >TSVOD platform"); 
				   		   	reporter.log(platFormArr[i][0]+TVODVarient+" > TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		  
		   		   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
		   		   		String [] STVODVarientarr = xlRead("STVODVarient").split(";");
		   		   		for(String STVODVarient :STVODVarientarr){
		   		   			result =addtoSchedule.setPlatform(platFormArr[i][0]+STVODVarient+" > SVOD/TVOD");
			   		   		Assert.assertTrue(result, "Failed to set "+ platFormArr[i][0]+STVODVarient+" >SVOD/TVOD platform"); 
				   		   	reporter.log(platFormArr[i][0]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	if(xlRead("EST").equalsIgnoreCase("Yes")){
		   		   		String [] ESTVarientarr = xlRead("ESTVarient").split(";");
		   		   		for(String ESTVarient :ESTVarientarr){
		   		   			result =addtoSchedule.setPlatform(platFormArr[i][0]+ESTVarient+" > EST");
			   		   		Assert.assertTrue(result, "Failed to set "+ platFormArr[i][0]+ESTVarient+" >EST platform"); 
				   		   	reporter.log(platFormArr[i][0]+ESTVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		}
		   			
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
				   	
				   	result = addtoSchedule.setStartingDate(strStartingDate);
				   	Assert.assertTrue(result, "Failed to Enter Starting Date"); 
				   	reporter.log("Starting Date Entered Successfully");	
				   	
					result = addtoSchedule.setEndingDate(strEndingDate);
				   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
				   	reporter.log("Ending Date Entered Successfully");	
				  		   	
				   	reporter.logWithScreenshot("Data Successfully Entered on Add To Schedule Page");
				   	
				   	result = addtoSchedule.clickSubmit();
				   	Assert.assertTrue(result, "Failed to click submit button"); 
				   	reporter.log("Submit button clicked Successfully");	
				   	
				   	result = main.clickSchedulePage();
					Assert.assertTrue(result, "Failed to click Schedule Page button");
					reporter.log("Schedule Page button Clicked Successfully");
					
					StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver); 
				   	
					for(int i = 1; i<=EpiNum;i++){
						result =  scheduleEntry.setUpdatePlatformId(xlRead("Episode"+i+"Name"));
					   	Assert.assertTrue(result, "Platform Entries not Available"); 
					   	reporter.log("Platform ID's Collected Successfully");
						
					}
				   	
					Thread.sleep(60000);
				   	
				   	driver.navigate().refresh();
				   	
				   	Thread.sleep(3000);
				   	
				    vospPlatform =scheduleEntry.getVospPlatformId();
				    String Flag = xlRead("Funtionality");
				   	for(int i = 0 ;i < vospPlatform.size();i++){
				   		
				   		result =  scheduleEntry.ValidateMessage(vospPlatform.get(i));
				   		Assert.assertTrue(result, "Presentation Video quality not Populated"); 
					   	reporter.log("Presentation Video Quality populated succeessfully");
					   	
					   	String EpiName  = scheduleEntry.getEpiName(vospPlatform.get(i));
					   	
					   	result = scheduleEntry.clickCheckBox(vospPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");
					   	
					   	result = scheduleEntry.clickEditbtn();
					   	Assert.assertTrue(result, "Failed to click Edit Button"); 
					   	reporter.log("Edit Button clicked succeessfully");
					   	
					   	PlatformVarient = scheduleEntry.getPlatformVarient();
					   	PlatformPresentationVideoQuality = scheduleEntry.getPlatformPresentationVideoQuality();
						String strContract = null;
					   	StagingEditSchedule editSchedule  = new StagingEditSchedule(driver); 
					   	
					   	
					 	result = editSchedule.setAsset(vospPlatform.get(i),EpiName);
				   		Assert.assertTrue(result, "Failed to SVOD Contract for Scheduling ID : "+vospPlatform.get(i)); 
					   	reporter.log("Contract Set succeessfully");
					   	
					   	if(PlatformVarient.get(vospPlatform.get(i)).equals("SVOD")){
					   		strContract = xlRead("SVODContract");
					   		result = editSchedule.setContract(strContract, vospPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to SVOD Contract for Scheduling ID : "+vospPlatform.get(i)); 
						   	reporter.log("Contract Set succeessfully");
						   	
						    String strSubs = xlRead("Product_Subscription");
					   		result = editSchedule.setSubscriptions(strSubs, vospPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
						   	reporter.log("Contract Set succeessfully");
					   		
					   	}else if(PlatformVarient.get(vospPlatform.get(i)).equals("TVOD")){
					   		strContract = xlRead("TVODContract");
					   		result = editSchedule.setContract(strContract, vospPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
						   	reporter.log("TVOD Contract Set succeessfully");
						   	
						    String strPrice=null;
						   	if(xlRead("TVODVarient").contains(";")){
						   		String [] TVODPricearr = xlRead("TVODPrice").split(";");
						   		if(PlatformPresentationVideoQuality.get(vospPlatform.get(i)).equals("SD")){
						   			strPrice = TVODPricearr[0];
						   		}else{
						   			strPrice = TVODPricearr[1];
						   		}
						   	}else{
						   		strPrice = xlRead("TVODPrice");
						   	}
						  
					   		result = editSchedule.setPrice(strPrice, vospPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
					   		
					   		
					   	}else if(PlatformVarient.get(vospPlatform.get(i)).equals("SVOD/TVOD")){
					   		strContract = xlRead("STVODContract");
					   		result = editSchedule.setContract(strContract, vospPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
						   	reporter.log("TVOD Contract Set succeessfully");
						   	
						    String strSubs = xlRead("Product_Subscription");
					   		result = editSchedule.setSubscriptions(strSubs, vospPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
						   	reporter.log("Contract Set succeessfully");
						   	
						    String strPrice = xlRead("STVODPrice");
					   		result = editSchedule.setPrice(strPrice, vospPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
						   	
					   	}else if(PlatformVarient.get(vospPlatform.get(i)).equals("EST")){
					   		strContract = xlRead("ESTContract");
					   		result = editSchedule.setContract(strContract, vospPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set EST Contract"); 
						   	reporter.log("EST Contract Set succeessfully");
						   	
						   	String strPrice=null;
						   	if(xlRead("ESTVarient").contains(";")){
						   		String [] strPricearr = xlRead("ESTPrice").split(";");
						   		
						   		if(PlatformPresentationVideoQuality.get(vospPlatform.get(i)).equals("SD")){
						   			strPrice = strPricearr[0];
						   		}else{
						   			strPrice = strPricearr[1];
						   		}
						   	}else{
						   		strPrice = xlRead("ESTPrice");
						   	}
						   	
					   		result = editSchedule.setPrice(strPrice, vospPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
					   		
					   	}
					   	
					   	String strPlacement = xlRead("Title_Placement");
				   		result = editSchedule.setPlacement(strPlacement, vospPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to Select Placement"); 
					   	reporter.log("Contract Set succeessfully");
					   	
					   	reporter.logWithScreenshot("Edit Shedule Page");
					   	
				//	   	result = editSchedule.clickSubmit();
				   		Assert.assertTrue(result, "Failed to click Update Button"); 
					   	reporter.log("Update button clicked succeessfully");
					   	
					   	Thread.sleep(3000);
				   		
				   	}
				   	
				   	for(int i=0;i<vospPlatform.size();i++){
				     	result = scheduleEntry.clickCheckBox(vospPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");
				    }
				    
				    result = scheduleEntry.clickSendtoSequencebtn();
				    Assert.assertTrue(result, "Failed to click on Send to Sequence Button"); 
				   	reporter.log("Send to Sequence clicked succeessfully");
				    
				   	String strWorkFlowTemplate = "";
				   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
				   		strWorkFlowTemplate ="MediaConnectDefault";
				   		if(xlRead("STVODVarient").contains("UHD")){
				   			strWorkFlowTemplate ="Youview Player Only";
				   		}
				   	}else{
				   		strWorkFlowTemplate= "MediaConnectDefault";
				   	}
				   	result = scheduleEntry.selectWorkflowTemplate(strWorkFlowTemplate);
				    Assert.assertTrue(result, "Failed to Select  on Send to Sequence Button"); 
				   	reporter.log("Send to Sequence clicked succeessfully");
				    
				    result = scheduleEntry.clickSendtoSequencebtnPage();
				    Assert.assertTrue(result, "Failed to click on Send to Sequence Button"); 
				   	reporter.log("Send to Sequence clicked succeessfully");
				   	
				    driver.navigate().refresh();
				    
				    youviewPlatform =scheduleEntry.getYouviewPlatformId();
				    StagingEditSchedule editSchedule  = new StagingEditSchedule(driver);
				   	for(int i = 0 ;i < youviewPlatform.size();i++){
				   		
				   		result =  scheduleEntry.ValidateMessage(youviewPlatform.get(i));
				   		Assert.assertTrue(result, "Presentation Video quality not Populated"); 
					   	reporter.log("Presentation Video Quality populated succeessfully");
					   	
					 	String EpiName  = scheduleEntry.getEpiName(vospPlatform.get(i));
					 	
					   	result = scheduleEntry.clickCheckBox(youviewPlatform.get(i));
					   	Assert.assertTrue(result, "Failed to click on Checkbox"); 
					   	reporter.log("Checkbox clicked succeessfully");
					   	
					   	result = scheduleEntry.clickEditbtn();
					   	Assert.assertTrue(result, "Failed to click Edit Button"); 
					   	reporter.log("Edit Button clicked succeessfully");
					   	
					   	PlatformVarient = scheduleEntry.getPlatformVarient();
					   	PlatformPresentationVideoQuality = scheduleEntry.getPlatformPresentationVideoQuality();
						String strContract = null;
					   	 
					   	
					   	result = editSchedule.setAsset(youviewPlatform.get(i),EpiName);
				   		Assert.assertTrue(result, "Failed to SVOD Contract for Scheduling ID : "+youviewPlatform.get(i)); 
					   	reporter.log("Contract Set succeessfully");
					   	
					   	if(PlatformVarient.get(youviewPlatform.get(i)).equals("SVOD")){
					   		strContract = xlRead("SVODContract");
					   		result = editSchedule.setContract(strContract, youviewPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to SVOD Contract for Scheduling ID : "+youviewPlatform.get(i)); 
						   	reporter.log("Contract Set succeessfully");
						   	
						    String strSubs = xlRead("Product_Subscription");
					   		result = editSchedule.setSubscriptions(strSubs, youviewPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
						   	reporter.log("Contract Set succeessfully");
					   		
					   	}else if(PlatformVarient.get(youviewPlatform.get(i)).equals("TVOD")){
					   		strContract = xlRead("TVODContract");
					   		result = editSchedule.setContract(strContract, youviewPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
						   	reporter.log("TVOD Contract Set succeessfully");
						   	
						    String strPrice=null;
						   	if(xlRead("TVODVarient").contains(";")){
						   		String [] TVODPricearr = xlRead("TVODPrice").split(";");
						   		if(PlatformPresentationVideoQuality.get(youviewPlatform.get(i)).equals("SD")){
						   			strPrice = TVODPricearr[0];
						   		}else{
						   			strPrice = TVODPricearr[1];
						   		}
						   	}else{
						   		strPrice = xlRead("TVODPrice");
						   	}
						  
					   		result = editSchedule.setPrice(strPrice, youviewPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
					   		
					   		
					   	}else if(PlatformVarient.get(youviewPlatform.get(i)).equals("SVOD/TVOD")){
					   		strContract = xlRead("STVODContract");
					   		result = editSchedule.setContract(strContract, youviewPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set TVOD Contract"); 
						   	reporter.log("TVOD Contract Set succeessfully");
						   	
						    String strSubs = xlRead("Product_Subscription");
					   		result = editSchedule.setSubscriptions(strSubs, youviewPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to SVOD Contract"); 
						   	reporter.log("Contract Set succeessfully");
						   	
						    String strPrice = xlRead("STVODPrice");
					   		result = editSchedule.setPrice(strPrice, youviewPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
						   	
					   	}else if(PlatformVarient.get(vospPlatform.get(i)).equals("EST")){
					   		strContract = xlRead("ESTContract");
					   		result = editSchedule.setContract(strContract, youviewPlatform.get(i));
					   		Assert.assertTrue(result, "Failed to set EST Contract"); 
						   	reporter.log("EST Contract Set succeessfully");
						   	
						   	String strPrice=null;
						   	if(xlRead("ESTVarient").contains(";")){
						   		String [] strPricearr = xlRead("ESTPrice").split(";");
						   		
						   		if(PlatformPresentationVideoQuality.get(youviewPlatform.get(i)).equals("SD")){
						   			strPrice = strPricearr[0];
						   		}else{
						   			strPrice = strPricearr[1];
						   		}
						   	}else{
						   		strPrice = xlRead("ESTPrice");
						   	}
						   	
					   		result = editSchedule.setPrice(strPrice, youviewPlatform.get(i),Flag);
					   		Assert.assertTrue(result, "Failed to Select Price"); 
						   	reporter.log("Price Set succeessfully");
					   		
					   	}
					   	
					   	String strPlacement = xlRead("Title_Placement");
				   		result = editSchedule.setPlacement(strPlacement, youviewPlatform.get(i));
				   		Assert.assertTrue(result, "Failed to Select Placement"); 
					   	reporter.log("Contract Set succeessfully");
					   	
					   	reporter.logWithScreenshot("Edit Shedule Page");
					   	
					//   	result = editSchedule.clickSubmit();
				   		Assert.assertTrue(result, "Failed to click Update Button"); 
					   	reporter.log("Update button clicked succeessfully");
					   	
					   	Thread.sleep(3000);
				   		
				   	}
				   	
				   	
				   	String strCollectionName = xlRead("AssetTitle");
				   	
					for(String key : editSchedule.AssetID.keySet()){
				  		
						String strAssetID =  editSchedule.getAssetID(key);
				  		youviewPlatform.clear();
				  		vospPlatform.clear();
				  		youviewPlatform = scheduleEntry.getYouviewRenditions(strAssetID);
					    vospPlatform =scheduleEntry.getVospRenditions(strAssetID);
				  		
				  		result = scheduleEntry.clickContentLink(key);
				  		Assert.assertTrue(result, "Failed to click Asset title Link"); 
				  		reporter.log("Asset title link clicked succeessfully");
				   	
				  		Thread.sleep(3000);
				  		
				  		
				  		result = main.clickNavLink("Renditions");
				  		Assert.assertTrue(result, "Failed to Click Renditions Link"); 
				  		reporter.log("Renditions Link Clicked Successfully");
				   	
				  		result = main.checkRenditions(Scheduling_Plaforms);
				  		Assert.assertTrue(result, "Renditions not Generated"); 
				  		reporter.log("Renditions generated Successfully");
				   	
				   	
				  		
				       
				  		if(Scheduling_Plaforms.equalsIgnoreCase("both")){
				  			result = main.validateBothRenditions(vospPlatform, youviewPlatform);
				  			Assert.assertTrue(result, "Renditions Validations got Failed"); 
				  			reporter.log("Renditions Validated Successfully");
				  		}else if(Scheduling_Plaforms.equalsIgnoreCase("VOSP")){
				  			result = main.validateVospRenditions(vospPlatform);
				  			Assert.assertTrue(result, "Renditions Validations got Failed"); 
				  			reporter.log("Renditions Validated Successfully");
					   	}
				  		
				  		result = main.clickCollSerlink(strCollectionName);
				  		Assert.assertTrue(result, "Failed to Click Collection or Series Link"); 
				 	   	reporter.log("Collection or Series Link Clicked Successfully");
				  		
				  		result = main.clickNavLink("Schedule");
				 	   	Assert.assertTrue(result, "Failed to Click Schedule Link"); 
				 	   	reporter.log("Schedule Link Clicked Successfully");
				 	   	
				 	    result = main.clickSchedulePage();
				 	   	Assert.assertTrue(result, "Failed to Click Schedule Page Button"); 
				 	   	reporter.log("Schedule Page Button Clicked Successfully");
				  
				  	}
					
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
		   			
		   		  return true;
		   	
			
		}catch(Exception e){
			return false;
		}
	}
	  
}
