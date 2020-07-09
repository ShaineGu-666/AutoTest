package dataTestSample;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;

import FileUtil.XmlUtil;

public class LoginDataProvider {

	private static Object[][] obj;
    private static List parList=new ArrayList();
    private static List sonList=new ArrayList();
    
    @DataProvider()
    public static Object[][] loginProvider(Method method){
    	
    	   //读取xml内容
        parList = XmlUtil.getXmlComent("C:/Users/Shaine.Gu/workspace/Lab1/src/main/java/dataTest/LoginTest.xml");
    	
        for (int i=0;i< parList.size();i++) {
            Map map = (Map)parList.get(i);
            if (map.containsKey(method.getName())) {
                Map<String,String> submap = (Map<String,String>) map.get(method.getName());
                sonList.add(submap);

            }
        }

        if (sonList.size() > 0) {
            
            obj = new Object[sonList.size()][];
            for (int i = 0; i < sonList.size(); i++) {
                obj[i] = new Object[] { sonList.get(i) };
            }
            return obj;
        }else{
            Assert.assertTrue(sonList.size()!=0,parList+"为空，找不到属性值："+method.getName() );
            return null;
        }

    	
    }
	
	
	
	
	
}
