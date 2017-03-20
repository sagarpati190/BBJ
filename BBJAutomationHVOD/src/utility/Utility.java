package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {
	public static String strScenerioName=null;
	public static int rowNum=0;
	//public static String desktopPath = (System.getProperty("user.home") + "\\desktop");
	public static String publicUserDir ;
    public static String BasePath;
    public static String DatablePath ;
    public static String UtilityPath;
    public static String ReportPath;
    public static String strVospRendition;
    public static String strUserDirectory;
    
    public static ArrayList<String> platformValueArr = new ArrayList<String>();
    
    public static void VMSetup(String userDirectory){
    	strUserDirectory =userDirectory;
    	publicUserDir = "C:\\E2E_Vision_Auto\\";
        BasePath = publicUserDir + "\\NewE2E_Vision_Auto\\"+strUserDirectory;
        DatablePath = BasePath+"\\DataTable";
        UtilityPath = BasePath+"\\Utility";
    }
    
    public static void localSetup(String userDirectory){
    	strUserDirectory =userDirectory;
    	publicUserDir = "C:\\Users\\Public";
        BasePath = publicUserDir + "\\NewE2E_Vision_Auto";
        DatablePath = BasePath+"\\DataTable";
        UtilityPath = BasePath+"\\Utility";
    }
    
    
    public static void setScenerioName(String strScenerioName){
		Utility.strScenerioName = strScenerioName;
	}
	
	public static String getScenerioName(){
		//strScenerioName = "Create New Single Standalone Title (Feature)_EST SD_ComingSoon";
		return strScenerioName;
	}
	
	
	public boolean buttonClickable(WebDriver driver,By Element) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.elementToBeClickable(Element));
			driver.findElement(Element);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/*Check element exist or not*/
	public boolean  existsElement(By by, WebDriver driver) {
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 120);
			wait1.until(ExpectedConditions.presenceOfElementLocated(by));
			driver.findElement(by);
			return  true;
		} 
		catch (Exception e) {
			return false;
		}
		
	}
	//********************************************************************************************************************
	
	/*read from excel(Using Column name)*/
	@SuppressWarnings("deprecation")
	public String xlRead(String colName){ 
		String value="";
		try{			
			int rownum=rowNum;// getRowCount();
			System.out.println("DatablePath-->>>>"+DatablePath+"\\BBJ BatchRunner"+strUserDirectory+".xls");
			Workbook workbook = WorkbookFactory.create(new FileInputStream(DatablePath+"\\BBJ BatchRunner"+strUserDirectory+".xls"));
			//Workbook workbook = WorkbookFactory.create(new FileInputStream("C:\\Users\\607518700\\Desktop\\E2E_Vision_Auto\\Data Table\\BBJ BatchRunner_User3.xls"));
			Sheet sheet = workbook.getSheet("Test Data");

			for(int cols=0;cols<100;cols++){
				String Field = sheet.getRow(9).getCell(cols).getStringCellValue();


				if(Field.equals(colName)){

					Cell cell= sheet.getRow(rownum).getCell(cols);
					System.out.println("Cell Type"+cell.getCellType());
					if( cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
						value= String.valueOf(cell.getNumericCellValue());
					}
					else if( cell.getCellType()==Cell.CELL_TYPE_STRING ){

						value= cell.getStringCellValue();
						System.out.println(value);

					}
					else if( cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
						value= cell.getStringCellValue();
					}
					else {

					}
					break;
				}


			} 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
		}
		return value; 
	}
	//*****************************************************************************************************************
	
	public String getOldDate(int numOfDays){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * numOfDays);
		Date date = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy-hh-mm"); 
		return format1.format(date);
	    
	}
	
	public String getFutureDate(int numOfDays){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numOfDays);
		Date date = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy-hh-mm"); 
		return format1.format(date);
	}
	
	public String getFutureTime(int Time){ 
        Calendar cal = Calendar.getInstance(); 
        cal.add(Calendar.HOUR, Time); 
        Date date = cal.getTime();             
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy-hh-mm"); 
        return format1.format(date); 
}

	
	/*read value from excel*/
	public String xlreadint(int row,int col){
		Workbook w;
		String v="";
		try {
			/*Time*/
			//System.out.println("Datapath====>"+DataPath);
			w = WorkbookFactory.create(new FileInputStream(DatablePath+"\\BBJ BatchRunner"+strUserDirectory+".xls"));
			//w = WorkbookFactory.create(new FileInputStream("C:\\Users\\607518700\\Desktop\\E2E_Vision_Auto\\Data Table\\BBJ BatchRunner_User3.xls"));
			Sheet sh = w.getSheet("Test Data"); 
			Cell cell =sh.getRow(row).getCell(col);

			if( cell.getCellType()==Cell.CELL_TYPE_NUMERIC){

				v= String.valueOf(cell.getNumericCellValue());
			}
			else if( cell.getCellType()==Cell.CELL_TYPE_STRING ){

				v= cell.getStringCellValue();
			}
			/*Path*/

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}	
	//*****************************************************************************************************************
	//-------------------------To write in Source file for SFG Check---------------------------------------------------
	
			public void ForSFGCheck(String Status, String SchedulingID){

				//This Module Log's writes the output data of the assets into SFG excel Source File 
				
				String SheetNameTitle ="Source";
				String value="";
				String Sourcepath=BasePath+"\\Source";
				try{
					Workbook workbook = WorkbookFactory.create(new FileInputStream(Sourcepath+"\\Source.xls"));
					 Sheet sheet = workbook.getSheet("Source");
					 
					 int cols=0;
					 for(int row=0;row<100;row++){
					       Cell cell= sheet.getRow(row).getCell(cols);
					       value= cell.getStringCellValue();
					       System.out.println("value"+value);
					       System.out.println("Cols"+cols);
					       
					       if (value!="") {
					    	   System.out.println("Next line");
					       }
					       else{
					    	   	   String IngestedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
						    	   System.out.println("IngestedTime"+IngestedTime);
						    	   
						    	   String testScenerio = xlRead("Test Scenario");
						    	   String Enviornment = xlRead("Enviornment");
						    	   String Funtionality = xlRead("Funtionality");
						    	   String ContentType = xlRead("ContentType");
						    	   String AssetName = xlRead("AssetTitle");
						    	   String AssetType = null;
						    	   String Platform = xlRead("Scheduling_Plaforms");
						    	   
						    	   if(xlRead("SVOD").equals("Yes")){
						    		   AssetType = "SVOD";
						    	   }else if(xlRead("TVOD").equals("Yes")){
						    		   AssetType = "TVOD";
						    		   if(xlRead("EST").equals("Yes")){
							    		   AssetType = "SmartAsset";
							    	   }
						    	   }else if(xlRead("STVOD").equals("Yes")){
						    		   AssetType = "STVOD";
						    	   }else if(xlRead("EST").equals("Yes")){
						    		   AssetType = "EST";
						    	   }
						    	   
						    	   String UpdateField = "NA";
						    	   String UpdateValue = "NA";
						    	   
						    	   if(Funtionality.equals("Update")){
						    		  		  
						    		   UpdateField = xlRead("UpdateFlag");
							    	   				    	   
							    	   if(UpdateField.equalsIgnoreCase("NA")){
							    		   System.out.println("Do Nothing");
							    	   }
							    	   else if(UpdateField.equalsIgnoreCase("Price")){
							    		   UpdateValue =  xlRead(AssetType+"Price");
							    	   }
							    	   else if(UpdateField.equalsIgnoreCase("Rating")){
							    		   UpdateValue =  xlRead("Asset_rating");
							    	   }
							    	   else if(UpdateField.equalsIgnoreCase("Subcription")){
							    		   UpdateValue =  xlRead("Product_Subscription");
							    	   }    		   
							    	    else if(UpdateField.equalsIgnoreCase("Placement")){
							    		   UpdateValue =  xlRead("Title_Placement");
							    		   
							    	   }
							    	    else if(UpdateField.equalsIgnoreCase("Metadata")){			
							    	   
							    	   }
							    	   else if(UpdateField.equalsIgnoreCase("ExpireAll")){
							    		   UpdateValue =  xlRead("ExpireWhole");
							    		   
							    	   }
							    	   else if(UpdateField.equalsIgnoreCase("ExpireOneEpisode")){
							    		   UpdateValue =  xlRead("Episode1Name");
						    	   }
						    	   }
						    	xlWrite(0,testScenerio,workbook,row);
						    	xlWrite(1,Enviornment,workbook,row);
						    	xlWrite(2,Funtionality,workbook,row);
						        xlWrite(3,ContentType,workbook,row);
								xlWrite(4,AssetName,workbook,row);
								xlWrite(5,AssetType,workbook,row);
								xlWrite(6,Platform,workbook,row);
								xlWrite(7,UpdateField,workbook,row);
								xlWrite(8,UpdateValue,workbook,row);
								xlWrite(9,Status,workbook,row);
								xlWrite(10,SchedulingID,workbook,row);
								xlWrite(11,IngestedTime,workbook,row);
					    	    break;
					       
						 }
					
					//Process process=Runtime.getRuntime().exec("cmd /c start /wait  \"\" \"" + "C:\\E2E_Vision_Auto\\SFGCheck\\Polling.bat"+"\"");
					//process.waitFor();
					 }
				}
				catch(Exception e){
					e.printStackTrace();
					
				}
			}		

	//-------------------------To write in Source file for SFG Check---------------------------------------------------
			
			//*****************************************************************************************************************
			/*read from excel*/
			public void xlWrite(int colName,String colValue,Workbook workbook,int rownum){
			try{
				// Get the first sheet.
				Sheet sheet = workbook.getSheet("Source");
				Cell cell2 = sheet.getRow(rownum).getCell(colName);	
				System.out.println("colValue"+colValue);
				//cell2.setCellValue(colValue+'\n');
				cell2.setCellValue(colValue);
				FileOutputStream fileOut = new FileOutputStream(BasePath+"\\Source\\Source.xls");
				workbook.write(fileOut);
				fileOut.close();
							
						
						
						
					
				// Write newly modified workbook to a file.
				
			}catch(Exception e)
			{
			}
			}
			//return SheetName; }
			
		//********************************************************************************************************************
}		

		
	
	
	

	
	
	  
	
	


