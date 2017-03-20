package pages.staging;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import utility.Utility;
public class StagingImages extends Utility  {
		WebDriver driver;
		
		By btnAddImage = By.id("bulk_upload_image_attachment");
		By txtImageName = By.name("bulk_upload_image[images_attributes][0][file_name]");
		By selectImageEncoding = By.name("bulk_upload_image[images_attributes][0][encoding]");
		By selectImageType = By.name("bulk_upload_image[images_attributes][0][image_kind_id]");
		By lnkEdit = By.xpath(".//li[@class='gallery-asset']//a[text()='Edit']");
		By btnCommit = By.className("button-submit");
		By btnReplace = By.id("image_attachment");
		
		static int noEdit;
		
		public StagingImages(WebDriver driver){
			this.driver = driver;
			noEdit =0;
		}
		
			
		public boolean clickAddImage(String strImgFilName){
			try{
				if(existsElement(btnAddImage, driver)){
					driver.findElement(btnAddImage).sendKeys(UtilityPath+"\\"+strImgFilName);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean editImage() {
			try {
				if(existsElement(lnkEdit,driver)) {
				//	noEdit = driver.findElements(lnkEdit).size();
					driver.findElements(lnkEdit).get(noEdit).click();
					noEdit = noEdit + 1;
					return true;
				}
				return false;
			} catch (Exception e) {
				return false;
			}

		}
		
		public boolean replaceImage(String strImgName) {
			try {
				if(existsElement(btnReplace,driver)) {
					driver.findElement(btnReplace).sendKeys(UtilityPath+"\\"+strImgName);
					return true;
				}
				return false;
			} catch(Exception e) {
				return false;
			}
		}
		
		public boolean setImageName(String strImgName){
			try{
				if(existsElement(txtImageName, driver)){
					driver.findElement(txtImageName).clear();
					driver.findElement(txtImageName).sendKeys(strImgName);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean selectImageEncoding(String strImgEncoding){
			try{
				if(existsElement(selectImageEncoding, driver)){
					Select sele1 = new Select(driver.findElement(selectImageEncoding));
					sele1.selectByVisibleText(strImgEncoding);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		public boolean selectImageType(String strImgType){
			try{
				
				if(existsElement(selectImageType, driver)){
					Select sele1 = new Select(driver.findElement(selectImageType));
					sele1.selectByVisibleText(strImgType);
					return true;
				}
				return false;
			}catch(Exception e){
				return false;
			}
		}
		
		@SuppressWarnings("unchecked")
		public boolean setImagePlatform(String strPlatformLable, boolean addPlatformFlag){
			try{
				By chkImagePlatform = By.xpath("//label[contains(text(),'"+strPlatformLable+"')]");
				System.out.println("//label[contains(text(),'"+strPlatformLable+"')]");
				if(existsElement(chkImagePlatform, driver)){
					String forImagePlatform =  driver.findElement(chkImagePlatform).getAttribute("for");
					chkImagePlatform = By.id(forImagePlatform);
					if(addPlatformFlag){
						platformValueArr.add(driver.findElement(chkImagePlatform).getAttribute("value"));
					}
					driver.findElement(chkImagePlatform).click();
					return true;
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
}
