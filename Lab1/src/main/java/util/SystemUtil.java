package util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SystemUtil {
	
	private Properties resource = null;
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * load the test data in properties file
	 * @param fileName : The file name to load
	 * 
	 * 
	 */
	public Properties loadPropertiesResources(String fileName) {

		
		resource = new Properties();
			try {
				File file=new File("./testdata/"+fileName);	
				InputStream dataInput = new FileInputStream(file);
				resource.load(dataInput);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.info("Error:Not found properties file"+ fileName);
				log.info("Error:Not found properties file", e);
			}
		
		return resource;
	}
	
	
	
	
	public static void driverKiller() throws IOException, InterruptedException {
	  final String kill = "taskkill /IM ";
	  String processName = "IEDriverServer.exe"; //IE process
	  Runtime.getRuntime().exec(kill + processName); 
	  Thread.sleep(1000);
	  String processName2 = "chromedriver.exe"; //chrome process
	  Runtime.getRuntime().exec(kill + processName2); 
	  Thread.sleep(1000);
	}

	public static String getDatetime() {

		SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");

		return date.format(new Date());

	}
	
	
}
