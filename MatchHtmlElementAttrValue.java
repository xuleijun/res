import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
/** 
 * @use ����w��HTMLh~{�I�w�葮���I<P 
 * @ProjectName stuff 
 * @Author <a href="mailto:mhmyqn@qq.com">mikan</a></br> 
 * @Date 2012-11-19 ����08:27:24 </br> 
 * @FullName com.mmq.regex.MatchHtmlElementAttrValue.java </br> 
 * @JDK 1.6.0 </br> 
 * @Version 1.0 </br> 
 */  
public class MatchHtmlElementAttrValue {  
      
    /** 
     * ����w��HTMLh~{�I�w�葮���I<P 
     * @param source �v�C�z�I�����{ 
     * @param element h~{���� 
     * @param attr h~{�I�������� 
     * @return ����<P��\ 
     */  
    public static List<String> match(String source, String element, String attr) {  
        List<String> result = new ArrayList<String>();  
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";  
        Matcher m = Pattern.compile(reg).matcher(source);  
        while (m.find()) {  
            String r = m.group(1);  
            result.add(r);  
        }  
        return result;  
    }  
      
    public static void main(String[] args) {  
        String source = "<a title=�����̈�b href=''>aaa</a><a title='�k�����b' href=''>bbb</a>";  
        List<String> list = match(source, "a", "title");  
        System.out.println(list);  
    }  
}  