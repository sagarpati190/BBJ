package main;
// I made this change
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import utility.Utility;

public class BBJ extends Utility {

	public static void main(String[] args)  {
				
	    // TODO Auto-generated method stub
			
		//Comment	
		String strModName= args[0];
		Utility.rowNum = Integer.parseInt(args[1]) - 1;
		try{
			if(args[2].equalsIgnoreCase("yes")){
				Utility.VMSetup(args[3]);
			}
			else{
				Utility.localSetup("");
			}
		}catch(Exception e){
			System.out.println("If you are using VM then please pass the 3rd Parameter");
		}
		Utility util = new Utility();
		String ScenerioName = util.xlRead("Test Scenario");
		Utility.setScenerioName(ScenerioName);
		String Time = util.xlreadint(1,4); 
		Utility.ReportPath = BasePath+"\\ExecutionReports\\"+Time+"\\"+ScenerioName;
        String classname  = "testcases."+strModName;    
        XmlSuite suite = new XmlSuite();
        suite.setName("Bebanjo Suite");
        XmlTest test = new XmlTest(suite);
        test.setName(strModName);
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass(classname));
        test.setXmlClasses(classes) ;
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.setOutputDirectory(Utility.ReportPath);
        tng.run();
	}

}
