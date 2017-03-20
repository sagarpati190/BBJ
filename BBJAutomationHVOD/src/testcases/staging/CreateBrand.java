package testcases.staging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.staging.StagingAddAsset;
import pages.staging.StagingAddtoSchedule;
import pages.staging.StagingBrandMetadata;
import pages.staging.StagingCatalog;
import pages.staging.StagingCreateContent;
import pages.staging.StagingEditSchedule;
import pages.staging.StagingImages;
import pages.staging.StagingLogin;
import pages.staging.StagingMISReporting;
import pages.staging.StagingMain;
import pages.staging.StagingScheduleEntires;
import pages.staging.StagingTitle;
import pages.staging.StagingVosp;
import utility.AdvanceReporter;
import utility.Utility;

public class CreateBrand extends Utility{
	   WebDriver driver;
	   AdvanceReporter reporter;
	   private ArrayList<String> AllPlatform,vospPlatform,youviewPlatform;
	   private HashMap<String,String> PlatformVarient,PlatformPresentationVideoQuality =new HashMap<String,String>();  
	   boolean result = false;
	   String SchedulingID = null;
	  
	   @Test
	  public void CreateBrand()  {
		  	  
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
		  	  
		  	  StagingCatalog  catalog = new StagingCatalog(driver);
		  	  
		  	  result = catalog.navigatetoCatalog();
		  	  Assert.assertTrue(result, "Navigate to catalog failed"); 
		  	  reporter.log("navigate to catalog Successfully");
		  	  	  	  
		  	  result = catalog.clickCreateImport();
		  	  Assert.assertTrue(result, "Failed to click createImport button"); 
		  	  reporter.log("create Import Button Clicked Successfully");
		  	  String contentType = "Single";
		  	  if(getScenerioName().contains("Trailer")){
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
		   	  String strTitleName=xlRead("AssetTitle");
		   	  result = createContent.setBrandNameText(strTitleName);
		   	  Assert.assertTrue(result, "Failed to Enter Title Name"); 
		   	  reporter.log("Title Name Entered Successfully");
		   	  
		   	  String strLicensor=xlRead("Licensor");
		   	  if(!strLicensor.isEmpty()){
			   	  result = createContent.setLicensorBrand(strLicensor);
			   	  Assert.assertTrue(result, "Failed to Enter Licensor Name"); 
			   	  reporter.log("Licensor Name Entered Successfully");
		   	  }
		    /*  String strExternalID="BBJABCD458526";
		   	  result = createContent.setTitleExternalID(strExternalID);
		   	  Assert.assertTrue(result, "Failed to Enter External ID"); 
		   	  reporter.log("External ID Entered Successfully");*/
		   	  
		   	  result = createContent.clickSubmit();
		  	  Assert.assertTrue(result, "Failed to click Create Button"); 
		   	  reporter.logWithScreenshot("Create Button Clicked Successfully");
		   /* --------------------------Create Page End ------------------------------------- */   	  
		   	  
		   	  StagingMain main = new StagingMain(driver);
		   	  String strVOSP_Version = xlRead("VOSP_Version");
		   	  System.out.println("VOSP_Version:"+strVOSP_Version);
		   	 
		   	  		   	
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
		   			imagesArr = new String [1][5];
		   			imagesArr[0][0]= "HiresImage.jpg";
		   			imagesArr[0][1]= "HiresImage.jpg";
		   			imagesArr[0][2]= "HiRes Packshot";
		   			imagesArr[0][3]= "VOSP2 > ";
		   			imagesArr[0][4]= "YouView > ";
		   				   					   			
		   		}
		   		
		   		System.out.println("imagesArr.length:"+imagesArr.length);
		   		boolean addPlatformFlag = true;
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
		   		   			result =images.setImagePlatform(imagesArr[i][3]+SVODVarient+" > SVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+SVODVarient+" > SVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+SVODVarient+" > SVOD platform Set Successfully");
				   		   	
				   		   	result =images.setImagePlatform(imagesArr[i][4]+SVODVarient+" > SVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][4]+SVODVarient+" > SVOD platform"); 
				   		   	reporter.log(imagesArr[i][4]+SVODVarient+" > SVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	
		   		   	if(xlRead("TVOD").equalsIgnoreCase("Yes")){
		   		   		String [] TVODVarientarr = xlRead("TVODVarient").split(";");
		   		   		for(String TVODVarient :TVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+TVODVarient+" > TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+TVODVarient+" >TSVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+TVODVarient+" > TVOD platform Set Successfully");
				   		   	
				   		    result =images.setImagePlatform(imagesArr[i][4]+TVODVarient+" > TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][4]+TVODVarient+" >TSVOD platform"); 
				   		   	reporter.log(imagesArr[i][4]+TVODVarient+" > TVOD platform Set Successfully");
				   		   	
				   		   	
		   		   		}
		   		   	}
		   		  
		   		   	if(xlRead("STVOD").equalsIgnoreCase("Yes")){
		   		   		String [] STVODVarientarr = xlRead("STVODVarient").split(";");
		   		   		for(String STVODVarient :STVODVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+STVODVarient+" >SVOD/TVOD platform"); 
				   		   	reporter.log(imagesArr[i][3]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
				   		   	
				   		    result =images.setImagePlatform(imagesArr[i][4]+STVODVarient+" > SVOD/TVOD",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][4]+STVODVarient+" >SVOD/TVOD platform"); 
				   		   	reporter.log(imagesArr[i][4]+STVODVarient+" > SVOD/TVOD platform Set Successfully");
		   		   		}
		   		   	}
		   		   	if(xlRead("EST").equalsIgnoreCase("Yes")){
		   		   		String [] ESTVarientarr = xlRead("ESTVarient").split(";");
		   		   		for(String ESTVarient :ESTVarientarr){
		   		   			result =images.setImagePlatform(imagesArr[i][3]+ESTVarient+" > EST",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][3]+ESTVarient+" >EST platform"); 
				   		   	reporter.log(imagesArr[i][3]+ESTVarient+" > SVOD/TVOD platform Set Successfully");
				   		   	
				   		    result =images.setImagePlatform(imagesArr[i][4]+ESTVarient+" > EST",addPlatformFlag);
			   		   		Assert.assertTrue(result, "Failed to set "+ imagesArr[i][4]+ESTVarient+" >EST platform"); 
				   		   	reporter.log(imagesArr[i][4]+ESTVarient+" > SVOD/TVOD platform Set Successfully");
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
		   	
		   	result = main.clickNavLink("Brand");
		   	Assert.assertTrue(result, "Failed to Click Brand Link"); 
		   	reporter.log("Title	Image Clicked Successfully");
		   	
		   	result =  main.clickEditMetadata();
		   	Assert.assertTrue(result, "Failed to Click Metadata Button"); 
		   	reporter.log("Metadata Button Clicked Successfully");
		   	  
		   	StagingBrandMetadata brand = new StagingBrandMetadata(driver);
		   	
		   	  String strBrandShort = xlRead("Title_ShortDesc");
		   	  result = brand.setBrandShortDesc(strBrandShort);
		  	  Assert.assertTrue(result, "Failed to Enter Short Description"); 
		  	  reporter.log("Short Description Entered Successfully");
		  	  
		  	  String strBrandMedium = xlRead("Title_MediumDesc");
		  	  result = brand.setBrandMediumDesc(strBrandMedium);
		  	  Assert.assertTrue(result, "Failed to Enter Medium Description"); 
		  	  reporter.log("Medium Description Entered Successfully");
		  	  
		  	  String strBrandLong = xlRead("Title_LongDesc");
		  	  result = brand.setBrandLongDesc(strBrandLong);
		  	  Assert.assertTrue(result, "Failed to Enter Long Description"); 
		  	  reporter.log("Long Description Entered Successfully");
		   
		  	 result = brand.clickSubmit();
		  	 Assert.assertTrue(result, "Failed to click on Submit Button"); 
		  	 reporter.log("Submit Button Clicked Successfully");
		   	
		   	
		   	   	
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					
					  if(result){
					 	reporter.log("Status:Passed");
					 	//To write in the Source File
					 	
									   
					  }else{
						 reporter.logWithScreenshot("Script Failed at page");
						 reporter.log("Status:Failed");
						//To write in the Source File
						 	 
						 	
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
		
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  reporter = new AdvanceReporter(driver);
	  }

	  @AfterMethod
	  public void afterMethod(){
		  try {
			
			  driver.quit();
		  }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }

	  

}
