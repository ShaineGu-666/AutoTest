package util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class testMain {
	public static Logger logger;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initateLog4j();
		logger.info("log4j file is found");
	}
	
	
	public static void initateLog4j() {
		// TODO Auto-generated method stub
		
       logger=Logger.getLogger(testMain.class);
//       BasicConfigurator.configure();  \
       PropertyConfigurator.configure("log4j.properties");
      
      
  
	 }

}
