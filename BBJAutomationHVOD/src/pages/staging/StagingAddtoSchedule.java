package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utility.Utility;

public class StagingAddtoSchedule extends Utility {

	WebDriver driver;
	
	By txtStartingDateDay = By.id("multi_scheduling_put_up_3i");
	By txtStartingDateMonth = By.id("multi_scheduling_put_up_2i");
	By txtStartingDateYear = By.id("multi_scheduling_put_up_1i");
	By txtStartingDateHour = By.id("multi_scheduling_put_up_4i");
	By txtStartingDateMinute = By.id("multi_scheduling_put_up_5i");
	
	By txtEndingDateDay = By.id("multi_scheduling_take_down_3i");
	By txtEndingDateMonth = By.id("multi_scheduling_take_down_2i");
	By txtEndingDateYear = By.id("multi_scheduling_take_down_1i");
	By txtEndingDateHour = By.id("multi_scheduling_take_down_4i");
	By txtEndingDateMinute = By.id("multi_scheduling_take_down_5i");
	
	
	By spanAssetType = By.id("select2-multi_scheduling_asset_id-container");
	By divAssetValue = By.xpath("//ul[@id='select2-multi_scheduling_asset_id-results']/li[2]/ul/li");
	
	By btnCommit = By.className("button-submit");
	//By btnCommit = By.xpath("/*[@id='new_multi_scheduling']/div[22]/input");
	By selScheduleType = By.id("multi_scheduling_episode_selection_type");
	By txtMultiSchedulingEpi = By.id("multi_scheduling_episode_selection");
	
	public StagingAddtoSchedule(WebDriver driver){
		this.driver = driver;
	}
	
		
	public boolean clickPlatforms(){
		try{
			if(!platformValueArr.isEmpty()){
			for(int i = 0 ;i < platformValueArr.size();i++ ){
				String chkValue =  platformValueArr.get(i);
				By chkPlatforms = By.xpath("//input[@value='"+chkValue+"']");
				if(existsElement(chkPlatforms, driver)){
					driver.findElement(chkPlatforms).click();
				}
			}
			return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setStartingDate(String strStartingDate){
		try{
			if(existsElement(txtStartingDateDay, driver)){
				String []strStartingDatearr = strStartingDate.split("-");
				
				driver.findElement(txtStartingDateDay).clear();
				driver.findElement(txtStartingDateDay).sendKeys(strStartingDatearr[0]);
				
				driver.findElement(txtStartingDateMonth).clear();
				driver.findElement(txtStartingDateMonth).sendKeys(strStartingDatearr[1]);
				
				driver.findElement(txtStartingDateYear).clear();
				driver.findElement(txtStartingDateYear).sendKeys(strStartingDatearr[2]);
				
				driver.findElement(txtStartingDateHour).clear();
				driver.findElement(txtStartingDateHour).sendKeys(strStartingDatearr[3]);
				
				driver.findElement(txtStartingDateMinute).clear();
				driver.findElement(txtStartingDateMinute).sendKeys(strStartingDatearr[4]);
				
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean setEndingDate(String strEndingDate){
		try{
			if(existsElement(txtEndingDateDay, driver)){
				String []strEndingDatearr = strEndingDate.split("-");
				
				driver.findElement(txtEndingDateDay).clear();
				driver.findElement(txtEndingDateDay).sendKeys(strEndingDatearr[0]);
				
				driver.findElement(txtEndingDateMonth).clear();
				driver.findElement(txtEndingDateMonth).sendKeys(strEndingDatearr[1]);
				
				driver.findElement(txtEndingDateYear).clear();
				driver.findElement(txtEndingDateYear).sendKeys(strEndingDatearr[2]);
				
				driver.findElement(txtEndingDateHour).clear();
				driver.findElement(txtEndingDateHour).sendKeys(strEndingDatearr[3]);
				
				driver.findElement(txtEndingDateMinute).clear();
				driver.findElement(txtEndingDateMinute).sendKeys(strEndingDatearr[4]);
				
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean selectAssetValue(){
		try{
			if(existsElement(spanAssetType, driver)){
				driver.findElement(spanAssetType).click();
				if(existsElement(divAssetValue, driver)){
					driver.findElement(divAssetValue).click();
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
		
	}
	
	public boolean clickSubmit(){
		try{
			if(existsElement(btnCommit, driver)){
				driver.findElement(btnCommit).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean selectScheduleType(String strScheduleType){
		try{
			if(existsElement(selScheduleType, driver)){
				Select scheduleType = new Select(driver.findElement(selScheduleType));
				scheduleType.selectByVisibleText(strScheduleType);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
		
	}
	
	public boolean setEpisodes(String strAddEpisode){
		try{
			if(existsElement(txtMultiSchedulingEpi, driver)){
				driver.findElement(txtMultiSchedulingEpi).sendKeys(strAddEpisode);
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
		
	}
	
	
	public boolean setPlatform(String strPlatformLable){
		try{
			By chkPlatform = By.xpath("//label[contains(text(),'"+strPlatformLable+"')]");
			System.out.println("//label[contains(text(),'"+strPlatformLable+"')]");
			if(existsElement(chkPlatform, driver)){
				String forImagePlatform =  driver.findElement(chkPlatform).getAttribute("for");
				chkPlatform = By.id(forImagePlatform);
				driver.findElement(chkPlatform).click();
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
}
