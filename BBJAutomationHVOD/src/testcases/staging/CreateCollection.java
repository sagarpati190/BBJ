package testcases.staging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import pages.staging.StagingAddAsset;
import pages.staging.StagingAddtoSchedule;
import pages.staging.StagingCatalog;
import pages.staging.StagingCreateContent;
import pages.staging.StagingEditSchedule;
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

public class CreateCollection extends Utility {
	
	   WebDriver driver;
	   AdvanceReporter reporter;
	   private ArrayList<String> AllPlatform,vospPlatform,youviewPlatform;
	   private HashMap<String,String> PlatformVarient,PlatformPresentationVideoQuality =new HashMap<String,String>();    
	   boolean result = false;
	   StagingMain main = null;
	   String SchedulingID =null;
	   String[] Entertainment={"CATCH UP HIGHLIGHTS","GOLD","NATIONAL GEOGRAPHIC","DISCOVERY"};
	   String[] Sport={"SPORT HIGHLIGHTS","SPORT TRENDING","CL FINAL","CL SEMI FINALS"};
	   String[] Film={"FILM NEW RELEASES","SKY HIGHLIGHTS","CURZON MOST POPULAR","SKY BOX SET 1"};
	   String[] Kids={"NICKTOONS CATCH UP","CARTOON NETWORK CATCH UP","BOOMERANG CATCH UP"};
	  // String[] Store={"TOP 40","PLAYLISTS>POPULAR","PLAYLISTS>TIME TO RELAX","PLAYLISTS>PARTY","PLAYLISTS>LOVE SONGS","ARTIST PLAYLISTS","PLAYLISTS>RETRO HITS","PLAYLISTS>FESTIVALS","PLAYLISTS>AWARD NOMINEES","PLAYLISTS>TV SHOWS","PLAYLIST>ALL CATEGORIES","ALBUMS>NEW RELEASES","ALBUMS>MOST POPULAR","ALBUMS>POP","ALBUMS>R&B/HIP HOP","ALBUMS>INDIE / ROCK","ALBUMS>FOLK","ALBUMS>60S","ALBUMS>70S","ALBUMS>80S","ALBUMS>90S","CONCERTS","MUSIC ON FILM","KARAOKE!","POPULAR PLAYLISTS","LATEST ALBUMS","SPOTLIGHT INTERVIEWS","SPOTLIGHT 1","SPOTLIGHT 2","SPOTLIGHT 3","SPOTLIGHT 4","SPOTLIGHT 5","SPOTLIGHT 6","SPOTLIGHT 7","SPOTLIGHT 11","SPOTLIGHT 12","SPOTLIGHT 14","SPOTLIGHT 15"};
	   String[] Highlights={"EXPLORE AMC","SPORT TRENDING","BEST OF FILM","KIDS HIGHLIGHTS"};
	   String[] Store={"DISCOVERY"};
	   
  @Test
  public void CreateCollection() {
	 
	   
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
		  	  
		   	/* --------------------------Catalog Start ------------------------------------- */  
		  	  
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
		  	  
			   	  String strTitleSubgeneres = xlRead("Title_Subgenres");
			   	  System.out.println("Title_Subgenres:"+strTitleSubgeneres);
			  	  result = title.setTitleSubgeneres(strTitleSubgeneres);
			  	  Assert.assertTrue(result, "Failed to Select Title Sub genere"); 
			  	  reporter.log("Title Sub genere Selected Successfully");
			     
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
		  	  
			  	String strCarouselsInput1 = xlRead("Carousels_1");
			   	  System.out.println("Carousels_1:"+strCarouselsInput1);
			   	  if(strCarouselsInput1.equalsIgnoreCase("Entertainment")){
			   		String strCarousels_1 = (Entertainment[new Random().nextInt(Entertainment.length)]);
			   		result = title.setCarousels_1(strCarousels_1);
			   		Assert.assertTrue(result, "Failed to Enter Carousels_1"); 
			   		reporter.log("Carousels_1 value Entered Successfully");
			   	  }else if(strCarouselsInput1.equalsIgnoreCase("Sport"))
			   	  		{
				   			String strCarousels_1 = (Sport[new Random().nextInt(Sport.length)]);
				   			result = title.setCarousels_1(strCarousels_1);
				   			Assert.assertTrue(result, "Failed to Enter Carousels_1"); 
				   			reporter.log("Carousels_1 value Entered Successfully");
			   	  		}else if(strCarouselsInput1.equalsIgnoreCase("Film")){
			   	  			String strCarousels_1 = (Film[new Random().nextInt(Film.length)]);
			   	  			result = title.setCarousels_1(strCarousels_1);
			   	  			Assert.assertTrue(result, "Failed to Enter Carousels_1"); 
			   	  			reporter.log("Carousels_1 value Entered Successfully");
			   	  		}else if(strCarouselsInput1.equalsIgnoreCase("Kids")){
			   	  			String strCarousels_1 = (Kids[new Random().nextInt(Kids.length)]);
			   	  			result = title.setCarousels_1(strCarousels_1);
			   	  			Assert.assertTrue(result, "Failed to Enter Carousels_1"); 
			   	  			reporter.log("Carousels_1 value Entered Successfully");
			   	  		}else if(strCarouselsInput1.equalsIgnoreCase("Store")){
			   	  			String strCarousels_1 = (Store[new Random().nextInt(Store.length)]);
			   	  			result = title.setCarousels_1(strCarousels_1);
			   	  			Assert.assertTrue(result, "Failed to Enter Carousels_1"); 
			   	  			reporter.log("Carousels_1 value Entered Successfully");
			   	  		}
			  	 
			  
			  	  String strCarouselsInput2 = xlRead("Carousels_2");
			   	  System.out.println("strCarouselsInput2:"+strCarouselsInput2);
			   	  if(strCarouselsInput2.equalsIgnoreCase("Highlights")){
			   	  String strCarousels_2=(Highlights[new Random().nextInt(Highlights.length)]);
			  	  result = title.setCarousels_2(strCarousels_2);
			  	  Assert.assertTrue(result, "Failed to Enter Carousels_2"); 
			  	  reporter.log("Carousels_2 value Entered Successfully");
			   	  }
			  	String strProductSubscription = xlRead("Product_Subscription");
			  	 if(strProductSubscription.equalsIgnoreCase("BT Vision Music") || strProductSubscription.equalsIgnoreCase("BT Vision Kids") ){ 
				  	  result = title.setTitleArtistName("Automation");
				  	  Assert.assertTrue(result, "Failed to Enter Artist Name"); 
				  	  reporter.log("Artist Name Entered Successfully");
			  	 }
		  	 
//			  	 String EST = xlRead("EST");   // IF Asset is EST HD then we have to enter Linked Asset ID Value which is 7 digit and present in URL
//			  	 if(EST.equalsIgnoreCase("Yes")){
//			  		 String ESTVarient = xlRead("ESTVarient");
//			  		 if(ESTVarient.contains("HD")){
//			  			 String url = driver.getCurrentUrl();
//			  			 String LinkedAssetID = null;
//				  			Pattern p = Pattern.compile("(\\d+)");
//				  			Matcher m = p.matcher(url);
//				  			if (m.find()) {
//				  				LinkedAssetID =m.group();
//				  			}
//				  			
//				  			result = title.setTitleLinkedID(LinkedAssetID);
//						  	Assert.assertTrue(result, "Failed to Set Linked Asset ID"); 
//						  	reporter.log("Linked Asset ID set Successfully");
//			  		 }
			  		 
//			  	 } 
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
			 	result = asset.setAssetRating("15");
				Assert.assertTrue(result, "Failed to Select Asset Rating"); 
			   	reporter.log("Asset Rating Selected Successfully");
//			    if(EST.equalsIgnoreCase("Yes")){
//			  		String ESTVarient = xlRead("ESTVarient");
//			  		if(ESTVarient.contains("HD")){
//			  			String strAssetResolution = "HD";		  			
//			  			result = asset.setAssetResolution(strAssetResolution);
//			  			Assert.assertTrue(result, "Failed to Select Asset Resolution"); 
//			  		   	reporter.log("Asset Resolution Selected Successfully");
//			  		 }
//			  		 
//			  	 }
			    
			   	
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
			   		
			   		if(Scheduling_Plaforms.equalsIgnoreCase("both")||Scheduling_Plaforms.equalsIgnoreCase("BothandTVE")){
			   			
			   			imagesArr = new String [3][4];
			   			
			   			imagesArr[0][0]= "SampleImg.jpg";
			   			imagesArr[0][1]= "packshotImage.jpg";
			   			imagesArr[0][2]= "Packshot";
			   			imagesArr[0][3]= "VOSP2 > ";
			   			
			   			imagesArr[1][0]= "HiresImage.jpg";
			   			imagesArr[1][1]= "HiresImage.jpg";
			   			imagesArr[1][2]= "HiRes Packshot";	
			   			imagesArr[1][3]= "YouView > ";
			   			
			   			imagesArr[2][0]= "BackGroundImg.jpg";
			   			imagesArr[2][1]= "BackGroundImg.jpg";
			   			imagesArr[2][2]= "Background";	
			   			imagesArr[2][3]= "VOSP2 > ";
			   			
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
			    //For VOSP Version 2.1
		   		  result = main.clickNavLink("VOSP");
			   	  Assert.assertTrue(result, "Failed to Click VOSP Link"); 
			   	  reporter.log("VOSP Link Clicked Successfully");
		   	    	 
			   	  result =  main.clickEditMetadata();
			   	  Assert.assertTrue(result, "Failed to Click Metadata Button"); 
			   	  reporter.log("Metadata Button Clicked Successfully");
		   	  
		   	  //------------------------Vosp Page Start ------------------
			   	  StagingVosp vosp = new StagingVosp(driver);
			   	  result =  vosp.clickrdoVOSP();
			   	  Assert.assertTrue(result, "Failed to click VOSP Radio Button "); 
			   	  reporter.log("VOSP Radio Button Clicked Successfully");
			   	  
			   	  result =  vosp.clickUpdateButton();
			   	  Assert.assertTrue(result, "Failed to click Update Button "); 
			   	  reporter.logWithScreenshot("Update Button Clicked Successfully");
			   	 //------------------------Vosp Page End ------------------
		  	  }  	
		  	     driver.navigate().refresh();
		  	 StagingCatalog  catalog = new StagingCatalog(driver);
		  	 result = catalog.navigatetoCatalog();
		  	 Assert.assertTrue(result, "Navigate to catalog failed"); 
		  	 reporter.log("navigate to catalog Successfully");
		  	 
		  	 result = catalog.clickCreateImport();
		  	  Assert.assertTrue(result, "Failed to click createImport button"); 
		  	  reporter.log("create Import Button Clicked Successfully");
	  	  
		  	  String contentType ="Collection";
		  	  result = catalog.selectContentType(contentType);
		  	  Assert.assertTrue(result, "Failed to Select Create Feature"); 
		   	  reporter.logWithScreenshot("Create Feature Button Clicked Successfully");
		   	  
		   	  /* --------------------------Create Collection Page Start ------------------------------------- */   
		   	  StagingCreateContent createCollection = new StagingCreateContent(driver);	
		   	  
		   	  String strCollectionName=xlRead("AssetTitle");
		   	  
		   	  result = createCollection.setCollectionName(strCollectionName);
		   	  Assert.assertTrue(result, "Failed to Enter Collection Name"); 
		   	  reporter.log("Collection Name Entered Successfully");
	   	  
		   	 for(int j=1; j <= EpiNum ; j++ ){
		   	 
		   	  String EpisodeName  = "Episode" +j+"Name";
		   	  String strEpisodeName=xlRead(EpisodeName);
		   	  result = createCollection.addEpisodeCollection(strEpisodeName);
		   	  	   	  
		   	 }
		   	  result = createCollection.clickSubmitColl();
		  	  Assert.assertTrue(result, "Failed to click Create Button"); 
		   	  reporter.logWithScreenshot("Create Button Clicked Successfully");
		   	  	  
	       	 
	   /* --------------------------Create Page End ------------------------------------- */   	 
		   	StagingMain main = new StagingMain(driver);
		   	result = main.clickNavLink("Images");
		   	Assert.assertTrue(result, "Failed to Click Image Link"); 
		   	reporter.log("Title	Image Clicked Successfully");
		   	StagingImages images = new StagingImages(driver);
	   	
	   
		    /*------------------------Images Page Start ------------------*/	
		   	String Scheduling_Plaforms = null;
		    String strVOSP_Version = xlRead("VOSP_Version");
		   	if(strVOSP_Version.equalsIgnoreCase("2.1")){
		   		String[][] imagesArr = null;
		   		
		   		Scheduling_Plaforms = xlRead("Scheduling_Plaforms");
		   		
		   		if(Scheduling_Plaforms.equalsIgnoreCase("both") ||Scheduling_Plaforms.equalsIgnoreCase("BothandTVE")){
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
		   		   	
		   		   	boolean addPlatformFlag = true;
					if(imagesArr[i][2].equalsIgnoreCase("Background")){
		   		    	addPlatformFlag  = false;
		   		    }else{
		   		    	addPlatformFlag = true;
		   		    }
		   		 
		   		   	if(xlRead("SVOD").equalsIgnoreCase("Yes")){
		   		   		String [] SVODVarientarr = xlRead("SVODVarient").split(";");
		   		   		for(String SVODVarient :SVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+SVODVarient+" > SVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+SVODVarient+" > SVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+SVODVarient+" > SVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	
		   		   	if(xlRead("TVOD").equalsIgnoreCase("Yes")){
		   		   		String [] TVODVarientarr = xlRead("TVODVarient").split(";");
		   		   		for(String TVODVarient :TVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+TVODVarient+" > TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+TVODVarient+" >TSVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+TVODVarient+" > TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		  
		   		   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
		   		   		String [] STVODVarientarr = xlRead("STVODVarient").split(";");
		   		   		for(String STVODVarient :STVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+STVODVarient+" >SVOD/TVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	if(xlRead("EST").equalsIgnoreCase("Yes")){
		   		   		String [] ESTVarientarr = xlRead("ESTVarient").split(";");
		   		   		for(String ESTVarient :ESTVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+ESTVarient+" > EST",addPlatformFlag);
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
	  	  
		   	String url = driver.getCurrentUrl();
 			Pattern p = Pattern.compile("(\\d+)");
	  		Matcher m = p.matcher(url);
	  		if (m.find()) {
	  				SchedulingID ="movida_"+m.group()+"_"+xlRead("STVODVarient");
	  				System.out.println("SchedulingID:"+SchedulingID);
	  		}
	  			
			result = main.clickNavLink("Group");
		   	Assert.assertTrue(result, "Failed to Click Group Link"); 
		   	reporter.log("Group Link Clicked Successfully");
		   	
		   	result = main.clickEditMetadata();
			Assert.assertTrue(result, "Failed to Click Metadata "); 
		   	reporter.log("Group Link Clicked Successfully");
		   	
		   	
		   	StagingGroup group = new StagingGroup(driver);
		   	String strName = xlRead("AssetTitle");
		   	result= group.setName(strName);
		  	Assert.assertTrue(result, "Failed to Set Collection Name"); 
   		   	reporter.log("Collection name set Successfully");
   		   	
   		    result= group.setOriginalName(strName);
		  	Assert.assertTrue(result, "Failed to Set Collection Original Name"); 
		   	reporter.log("Collection Original name set Successfully");
		   
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
		   	
	   		String strSessionNumber = "1";
	   	   	result = group.setSessionNumber(strSessionNumber);
	   	   	Assert.assertTrue(result, "Failed to Enter session number"); 
	   	   	reporter.log("Session Number Entered Successfully");
		  
	   	   	String RollingFlag = xlRead("RollingFlag");
	   	   	if(RollingFlag.equalsIgnoreCase("yes")){
		   	 	result = group.setRollingFlag();
		   	   	Assert.assertTrue(result, "Failed to set rolling Flag"); 
		   	   	reporter.log("Rolling Flag set Successfully");
	   	   	}
	   	   	
	   	   	reporter.logWithScreenshot("Group Metadata Entered Successfully");
	   	   	
	   	   	result = group.clickUpdateButton();
	   	   	Assert.assertTrue(result, "Failed to click update button"); 
	   	   	reporter.log("Update button clicked Successfully");
	   	   	/* ---------------------- Group Page Ends ------------------- */
	   	   	   	
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
		   	
		   	String strStartingDate=null,strEndingDate=null;
		    String stdate = xlRead("StartDate");
		    String endate = xlRead("EndDate");
		   	if((!stdate.isEmpty()) && (!endate.isEmpty()))
		   	{
		   		strStartingDate = stdate;
		   	strEndingDate = endate;
		   		System.out.println("date assigned");
		   	}
		   	else if(getScenerioName().contains("ComingSoon")){
		   		strStartingDate = getFutureDate(2);
		   		strEndingDate = getFutureDate(90);
		   	}else{
		   		strStartingDate = getOldDate(1);
		   		strEndingDate = getFutureDate(90);
		   	}
		   	
		   	
		   	result = AddtoSchedule.setStartingDate(strStartingDate);
		   	Assert.assertTrue(result, "Failed to Enter Starting Date"); 
		   	reporter.log("Starting Date Entered Successfully");	
		   	
			result = AddtoSchedule.setEndingDate(strEndingDate);
		   	Assert.assertTrue(result, "Failed to Enter Ending Date"); 
		   	reporter.log("Ending Date Entered Successfully");	
		  		   	
		   	reporter.logWithScreenshot("Data Successfully Entered on Add To Schedule Page");
		   	
		   	result = AddtoSchedule.clickSubmit();
		   	Assert.assertTrue(result, "Failed to click submit button"); 
		   	reporter.log("Submit button clicked Successfully");	
		   	
		   	/*--------------- Schedule Page Ended -------------------------*/
		   	Thread.sleep(10000);
		   	result = main.clickSchedulePage();
		   	driver.navigate().refresh();
		   	Assert.assertTrue(result, "Failed to click submit button"); 
		   	reporter.log("Submit button clicked Successfully");	
		   	
		   	
		   	StagingScheduleEntires scheduleEntry = new StagingScheduleEntires(driver); 
		   	
		   	Thread.sleep(10000);
		   	driver.navigate().refresh();
		   	Thread.sleep(20000);
		   	result =  scheduleEntry.setPlatformId();
		   	Assert.assertTrue(result, "Platform Entries not Available"); 
		   	reporter.log("Platform ID's Collected Successfully");   	
		   	driver.navigate().refresh();
		   	
		   	Thread.sleep(3000);
		   	// to edit and update vosp entries
		   	
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
			   	result = editSchedule.setPresentationVideoQuality(PlatformPresentationVideoQuality.get(vospPlatform.get(i)),vospPlatform.get(i));
			    Assert.assertTrue(result, "Failed to Select Presentation Video Quality"); 
			   	reporter.log("Presentation Video Quality Set succeessfully");
			   	
			   	String strPlacement = xlRead("Title_Placement");
		   		result = editSchedule.setPlacement(strPlacement, vospPlatform.get(i));
		   		Assert.assertTrue(result, "Failed to Select Placement"); 
			   	reporter.log("Contract Set succeessfully");
			   	
			   	reporter.logWithScreenshot("Edit Shedule Page");
			   	
			   	result = editSchedule.clickSubmit(vospPlatform.get(i));
		   		Assert.assertTrue(result, "Failed to click Update Button"); 
			   	reporter.log("Update button clicked succeessfully");
			   	
			   	Thread.sleep(3000);
		   		
		   	}
		   	
		   	// to edit and update youview sntries
		    StagingEditSchedule editSchedule  = new StagingEditSchedule(driver);

		    youviewPlatform =scheduleEntry.getYouviewPlatformId();
			   
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
			   	result = editSchedule.setPresentationVideoQuality(PlatformPresentationVideoQuality.get(youviewPlatform.get(i)),youviewPlatform.get(i));
			    Assert.assertTrue(result, "Failed to Select Presentation Video Quality"); 
			   	reporter.log("Presentation Video Quality Set succeessfully");
			   	
			   	String strPlacement = xlRead("Title_Placement");
		   		result = editSchedule.setPlacement(strPlacement, youviewPlatform.get(i));
		   		Assert.assertTrue(result, "Failed to Select Placement"); 
			   	reporter.log("Contract Set succeessfully");
			   	
			   	reporter.logWithScreenshot("Edit Shedule Page");
			   	
			   	result = editSchedule.clickSubmit(youviewPlatform.get(i));
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
		  /* 	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
		   		strWorkFlowTemplate ="MediaConnectDefault";
		   		if(xlRead("STVODVarient").contains("UHD")){
		   			strWorkFlowTemplate ="Youview Player Only";
		   		}
		   	}else*/ 
		   	if(xlRead("Scheduling_Plaforms").equalsIgnoreCase("BothandTVE")){
		   		strWorkFlowTemplate = "TVE, Seaview & Youview Players";
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
		    for(String key : editSchedule.AssetID.keySet()){
					String strAssetID =  editSchedule.getAssetID(key);
	    	boolean FolderSD = false,FolderHD=false,FolderSDHD=false;
	        String MAMFolder="";
	        for (Entry<String, String> entry : PlatformPresentationVideoQuality.entrySet()) {
	           if(entry.getValue().equalsIgnoreCase("SD")){
	        	   FolderSD=true;
	        	   
	           }else if(entry.getValue().equalsIgnoreCase("HD")){
	        	   FolderHD=true;
	           }
	        }
	        if(FolderSD && FolderHD){
	        	FolderSDHD=true;
	        }
	        
	        if(FolderSDHD){
	        	MAMFolder = "E2E_STEREO_HD_SD";
	        }else if(FolderSD){
	        	MAMFolder = "E2E_STEREO_SD_only";
	        }else if(FolderHD){
	        	MAMFolder = "E2E_STEREO_HD_only";
		        }
			    result = scheduleEntry.uploadFile(strAssetID,MAMFolder);
			    Assert.assertTrue(result, "Failed to upload media file"); 
		   	reporter.log("Media File uploaded succeessfully");
		   	
		   	Thread.sleep(5000);
		   	if(xlRead("Scheduling_Plaforms").equalsIgnoreCase("BothandTVE")){
		   		MAMFolder = "E2E_STEREO_ABR_HLS";
		   		result = scheduleEntry.uploadFile(strAssetID,MAMFolder);
			    Assert.assertTrue(result, "Failed to upload media file"); 
			   	reporter.log("Media File uploaded succeessfully");
		   	
		   	}
		    }
	    
	
		    
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
  
  @BeforeMethod
  public void beforeMethod() {
  
	  ProfilesIni profile = new ProfilesIni();
	  FirefoxProfile myprofile = profile.getProfile("default");
	  
	  driver = new FirefoxDriver(myprofile);
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  Utility.setScenerioName("CreateSingle");
	  reporter = new AdvanceReporter(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  
	  try {
			
		  driver.quit();
	  }catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }

}
