package FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * use saxReader to explain the XML file
 * @author shaine.gu
 *
 */
public class XmlUtil {
	public static List getXmlComent(String fname){
        //获取xml文件完全路径
		File file=new File(fname);
//        String path=file.getAbsolutePath();
		 String path=file.getPath();
        System.out.println("xml·path"+path);
        
        List contList=new ArrayList();
        //dom4j中读取xml文件的方法
        SAXReader saxR=new SAXReader();
        try {
            Document doc=saxR.read(path);
            //存放顶结点
            Element eleroot=doc.getRootElement();
            //parMap，存放顶结点下一级结点
            Map parMap=new HashMap();
            Map sonMap=new HashMap();
            for(Iterator i=eleroot.elementIterator();i.hasNext();){
                //parMap中存放的结点的子结点
                
                Element elepar=(Element)i.next();
                for(Iterator j=elepar.elementIterator();j.hasNext();){
                    Element eleSon=(Element)j.next();
                    System.out.println("+++++"+eleSon.getName()+"    "+ eleSon.getText());
                    sonMap.put(eleSon.getName(), eleSon.getText());
                }
                
                parMap.put(elepar.getName(),sonMap);
                System.out.println("*****"+elepar.getName());
                contList.add(parMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        
        return contList;
    }


}
