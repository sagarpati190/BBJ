package pages.staging;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Utility;

public class StagingMain extends Utility {
	WebDriver driver;
	By navLinksclass = By.className("nav-aside-link");
	By btnEditMetadata = By.xpath("html/body/div[4]/div[3]/div[2]/div[1]/div[2]/ul/li/a");
	By linkAddAsset = By.partialLinkText("Add Asset");
	By linkAddtoSchedule = By.linkText("Add to Schedule");
	By linkSchedulePage = By.linkText("Schedule Page");
	By linkCollSer = By.cssSelector("profile-part-of>li>a");
	By editAsset = By.xpath(".//a[@title='Edit']");
	By linkEditTitles = By.partialLinkText("Edit Titles");
	By btnMainEdit = By.xpath("html/body/div[4]/div[2]/div/div[1]/div[2]/form/a");

	
	By youviewRenditions = By.className("rendition-entry-asset-name");
	By vospRenditions = By.className("rendition-entry-external-id");
	
	
	
	public StagingMain(WebDriver driver){
		this.driver = driver;
	}

	public boolean clickNavLink(String strLinkText){
		try{
			boolean validatePageHeading = true;
			int counter = 0; 
			if(existsElement(navLinksclass, driver)){
				List<WebElement> navLinks = driver.findElements(navLinksclass);
				for(WebElement navLink: navLinks ){
					if(navLink.getText().contains(strLinkText)){
						Thread.sleep(2000);
						if(navLink.isEnabled()){
							navLink.click();
							while(validatePageHeading && existsElement(navLinksclass, driver) && counter < 10 ){
								String strHeading = driver.findElement(By.className("page-heading")).getText();
								if(strHeading.contains(strLinkText)){
									return true;
								}else{
									navLink.click();
									counter++;
								}
							}
							
						}
					}
				}
			}
		return false;
		}catch(Exception e){
			return false;
		}
		
	}
	
	public boolean clickEditMetadata(){
		try{
			if(existsElement(btnEditMetadata, driver)){
				driver.findElement(btnEditMetadata).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean clickAddAsset(){
		try{
			if(existsElement(linkAddAsset, driver)){
				driver.findElement(linkAddAsset).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean editAsset() {
		try {
			if(existsElement(editAsset,driver)) {
				driver.findElement(editAsset).click();
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	public boolean clickAddtoSchedule(){
		try{
			if(existsElement(linkAddtoSchedule, driver)){
				driver.findElement(linkAddtoSchedule).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	
	public boolean clickSchedulePage(){
		try{
			if(existsElement(linkSchedulePage, driver)){
				Thread.sleep(2000);
				driver.findElement(linkSchedulePage).click();
			
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean clickCollSerlink(String strlinkCollSer){
		try{
			By linkCollSer = By.partialLinkText(strlinkCollSer);
			if(existsElement(linkCollSer, driver)){
				if(driver.findElement(linkCollSer).getText().equals(strlinkCollSer)){
					driver.findElement(linkCollSer).click();
					return true;
				}
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean validateVospRenditions(ArrayList<String> vospPlatform){
		try{
			if(existsElement(vospRenditions, driver)){
				List <WebElement> listVospRenditions =driver.findElements(vospRenditions);
				if(!listVospRenditions.isEmpty()){
					List <WebElement> listYouviewRenditions =driver.findElements(youviewRenditions);
					if(vospPlatform.size() == listVospRenditions.size()){
						for(int i = 0;i<vospPlatform.size();i++){
							String strVospRendition = listVospRenditions.get(i).getText();
							String strYouviewRendition =listYouviewRenditions.get(i).getText();
							if(! strVospRendition.equals(vospPlatform.get(i)) && strYouviewRendition.contains("File name not set yet")  ){
								return false;
							}
						}
						
					}else{
						System.out.println("Renditions created are not matched ");
						return false;
					}
				}else{
					return false;
				}
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	
	public boolean validateBothRenditions(ArrayList<String> vospPlatform,ArrayList<String> youviewPlatform){
		try{
			if(existsElement(vospRenditions, driver)){
				List <WebElement> listVospRenditions =driver.findElements(vospRenditions);
				if(!listVospRenditions.isEmpty()){
					List <WebElement> listYouviewRenditions =driver.findElements(youviewRenditions);
					if(vospPlatform.size() == listVospRenditions.size()){
						for(int i = 0;i<vospPlatform.size();i++){
							String strVospRendition = listVospRenditions.get(i).getText();
							String strYouviewRendition =listYouviewRenditions.get(i).getText();
							if(! strVospRendition.equals(vospPlatform.get(i)) ||  ! strYouviewRendition.equals(youviewPlatform.get(i))){
								return false;
							}
						}
						
					}else{
						System.out.println("Renditions created are not matched ");
						return false;
					}
				}else{
					return false;
				}
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean checkRenditions(String Platform){
		try{
			for(int i=0;i<60;i++){
				boolean flag = true;;	
				driver.navigate().refresh();
				Thread.sleep(2000);
				if(existsElement(vospRenditions, driver)){
					if(Platform.equalsIgnoreCase("both") || Platform.equalsIgnoreCase("Youview")){
						List <WebElement> listYouviewRenditions = driver.findElements(youviewRenditions);
						if(! listYouviewRenditions.isEmpty()){
							for(WebElement youviewRenditions : listYouviewRenditions){
								String strYouviewRendition =youviewRenditions.getText();
								if(strYouviewRendition.contains("File name not set yet")){
									flag = false;
									break;
								}else{
									flag = true;
								}
								
							}
					     }
					}else{
						List <WebElement> listYouviewRenditions = driver.findElements(youviewRenditions);
						if(! listYouviewRenditions.isEmpty()){
							for(WebElement youviewRenditions : listYouviewRenditions){
								String strYouviewRendition =youviewRenditions.getText();
								if(strYouviewRendition.contains("File name not set yet")){
									flag = true;
								}
								
							}
					     }
					}
					if(flag){
						return true; 
					}
				}
				Thread.sleep(10000);
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean clickEditTitles(){
		try{
			if(existsElement(linkEditTitles, driver)){
				driver.findElement(linkEditTitles).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public int getEpisodenum(){
		List<WebElement> rows = driver.findElements(By.className("model-feature"));
		System.out.println("Total number of rows :"+ rows.size());
		return rows.size();
		
	}
	
	public boolean clickEpiName(int EpiNum){
		try{
			By linkEpiName = By.xpath("//tr[@class='model-feature']["+EpiNum+"]//a");
			if(existsElement(linkEpiName, driver)){
				driver.findElement(linkEpiName).click();
				return true;
			}
		return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean ClickMainEdit(){ 
        try{ 
                if(existsElement(btnMainEdit, driver)){ 
                        driver.findElement(btnMainEdit).click(); 
                        return true; 
                } 
        return false; 
        }catch(Exception e){ 
                return false; 
        } 
}
	
	public boolean SetUpBrand(String strBrandName){ 
        try{ 
                By bybrand = By.id("select2-series_brand_name-container"); 
                if(existsElement(bybrand, driver)){ 
                        driver.findElement(bybrand).click(); 
                        By txtBrandInput = By.xpath("//*[@id='select2-series_brand_name-result-rrv5-"+strBrandName+"']/span"); 

                        if(existsElement(txtBrandInput, driver)){ 
                                        driver.findElement(txtBrandInput).click(); 
                                        return true; 
                        } 
                        return false; 
                } 
                return false; 
        }catch(Exception e){ 
                e.printStackTrace(); 
                return false; 
        } 
}



	
	
	
}
